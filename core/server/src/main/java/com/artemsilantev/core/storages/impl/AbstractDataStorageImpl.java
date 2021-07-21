package com.artemsilantev.core.storages.impl;

import com.artemsilantev.core.exceptions.LoadDataException;
import com.artemsilantev.core.filemanagers.FileManager;
import com.artemsilantev.core.handlers.Handler;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.BaseEntity;
import com.artemsilantev.core.storages.AbstractDataStorage;
import com.artemsilantev.core.validators.Validator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public abstract class AbstractDataStorageImpl<E extends BaseEntity>
    implements AbstractDataStorage<E> {

  private Long entityIdSequence;
  private Collection<E> entities;
  private final Handler<Mapper<E, String>, String> mapperHandler;
  private final FileManager fileManager;
  private final String pathToFile;
  private final Collection<Validator<String>> fileItemValidators;
  private final Collection<Validator<E>> entityValidators;


  protected AbstractDataStorageImpl(
      Handler<Mapper<E, String>, String> mapperHandler,
      FileManager fileManager,
      String pathToFile,
      Collection<Validator<String>> fileItemValidators,
      Collection<Validator<E>> entityValidators) {
    this.mapperHandler = mapperHandler;
    this.fileManager = fileManager;
    this.pathToFile = pathToFile;
    this.fileItemValidators = fileItemValidators;
    this.entityValidators = entityValidators;
  }

  @Override
  public synchronized Collection<E> getEntities() {
    if (entities == null) {
      entities = load();
      entityIdSequence = entities.stream()
          .mapToLong(E::getId)
          .max()
          .orElse(0L);
    }
    return entities;
  }

  @Override
  public E create(E entity) {
    getEntities().add(entity);
    entity.setId(++entityIdSequence);
    return entity;
  }

  @Override
  public void save() {
    Mapper<E, String> mapper = mapperHandler.getHandler(pathToFile);
    fileManager.write(pathToFile, mapper.toSourceCollection(entities));
  }

  protected Collection<E> load() {
    Mapper<E, String> mapper = mapperHandler.getHandler(pathToFile);
    try {
      Collection<E> entitiesToLoad = fileManager.read(pathToFile)
          .filter(this::validateFileItem)
          .map(entity -> {
            try {
              return mapper.toTarget(entity);
            } catch (Exception exception) {
              log.warn("Entity [{}] has problem with parsing:{}",
                  entity, exception.getMessage());
              return null;
            }
          })
          .filter(Objects::nonNull)
          .filter(this::validateEntity)
          .collect(Collectors.toList());
      return fillReference(entitiesToLoad);
    } catch (Exception exception) {
      log.error("Problem with loading data (path: \"{}\")", pathToFile);
      throw new LoadDataException(exception.getMessage());
    }
  }

  protected Collection<E> fillReference(Collection<E> entitiesToLoad) {
    return entitiesToLoad;
  }


  protected boolean validateFileItem(String text) {
    var problems = new ArrayList<String>();
    fileItemValidators.forEach(validator -> problems.addAll(validator.validate(text)));
    if (!problems.isEmpty()) {
      log.warn("Text [{}] has problems: {}", text,
          StringUtils.join(problems));
      return false;
    }
    return true;
  }

  protected boolean validateEntity(E entity) {
    var problems = new ArrayList<String>();
    entityValidators.forEach(validator -> problems.addAll(validator.validate(entity)));
    if (!problems.isEmpty()) {
      log.warn("Entity [{}] has problems: {}", entity.toString(),
          StringUtils.join(problems));
      return false;
    }
    return true;
  }
}
