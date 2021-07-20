package com.artemsilantev.core.storages.impl;

import com.artemsilantev.core.exceptions.AccessFileException;
import com.artemsilantev.core.exceptions.LoadDataException;
import com.artemsilantev.core.filemanagers.FileManager;
import com.artemsilantev.core.handlers.Handler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import com.artemsilantev.core.storages.AbstractDataStorage;
import com.artemsilantev.core.validators.Validator;

@Slf4j
public abstract class AbstractDataStorageImpl<E extends BaseEntity>
    implements AbstractDataStorage<E> {

  private Long entityIdSequence;
  private Collection<E> entities;
  private final Handler<Mapper<E, String>, String> mapperHandler;
  private final FileManager fileManager;
  private final String pathToFile;
  private final Collection<Validator<String>> textValidators;
  private final Collection<Validator<E>> entityValidators;


  protected AbstractDataStorageImpl(
      Handler<Mapper<E, String>, String> mapperHandler,
      FileManager fileManager,
      String pathToFile,
      Collection<Validator<String>> textValidators,
      Collection<Validator<E>> entityValidators) {
    this.mapperHandler = mapperHandler;
    this.fileManager = fileManager;
    this.pathToFile = pathToFile;
    this.textValidators = textValidators;
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
    entity.setId(++entityIdSequence);
    entities.add(entity);
    return entity;
  }

  @Override
  public void save() {
    Mapper<E, String> mapper = mapperHandler.getHandler(pathToFile);
    try {
      fileManager.write(pathToFile, entities.stream()
          .map(mapper::toSource)
          .collect(Collectors.toList())
      );
    } catch (IOException ioException) {
      log.error("Couldn't save data by path \"{}\" in {}", pathToFile,
          this.getClass().getSimpleName());
      throw new AccessFileException(
          String.format("File with path \"%s\" has problem: %s", pathToFile,
              ioException.getMessage()));
    }
  }

  protected Collection<E> load() {
    Mapper<E, String> mapper = mapperHandler.getHandler(pathToFile);
    try {
      return fileManager.read(pathToFile)
          .filter(this::validateParseItem)
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
    } catch (IOException ioException) {
      log.error("Problem with loading data (path: \"{}\")", pathToFile);
      throw new LoadDataException(ioException.getMessage());
    }
  }

  protected boolean validateParseItem(String text) {
    var problems = new ArrayList<String>();
    textValidators.forEach(textValidator -> problems.addAll(textValidator.validate(text)));
    if (!problems.isEmpty()) {
      log.warn("Text [{}] has problems: {}", text,
          StringUtils.join(problems));
      return false;
    }
    return true;
  }

  protected boolean validateEntity(E entity) {
    var problems = new ArrayList<String>();
        entityValidators.forEach(entityValidator -> problems.addAll(entityValidator.validate(entity)));
    if (!problems.isEmpty()) {
      log.warn("Entity [{}] has problems: {}", entity.toString(),
          StringUtils.join(problems));
      return false;
    }
    return true;
  }
}
