package storages.impl;

import configs.DataStorageConfiguration;
import exceptions.AccessFileException;
import exceptions.LoadDataException;
import handlers.Handler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import mappers.Mapper;
import model.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import storages.AbstractDataStorage;

@Slf4j
public abstract class AbstractDataStorageImpl<E extends BaseEntity>
    implements AbstractDataStorage<E> {

  private Long entityIdSequence;
  private Collection<E> entities;
  DataStorageConfiguration<E> configuration;


  protected AbstractDataStorageImpl(DataStorageConfiguration<E> configuration) {
    this.configuration = configuration;
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
    var fileManager = configuration.getFileManager();
    var pathToFile = configuration.getPathToFile();
    Handler<Mapper<E, String>, String> handler = configuration.getMapperHandler();
    Mapper<E, String> mapper = handler.getHandler(configuration.getPathToFile());
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
    var fileManager = configuration.getFileManager();
    var pathToFile = configuration.getPathToFile();
    Handler<Mapper<E, String>, String> handler = configuration.getMapperHandler();
    Mapper<E, String> mapper = handler.getHandler(configuration.getPathToFile());
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
    configuration.getTextValidators()
        .forEach(textValidator -> problems.addAll(textValidator.validate(text)));
    if (!problems.isEmpty()) {
      log.warn("Text [{}] has problems: {}", text,
          StringUtils.join(problems));
      return false;
    }
    return true;
  }

  protected boolean validateEntity(E entity) {
    var problems = new ArrayList<String>();
    configuration.getEntityValidators()
        .forEach(entityValidator -> problems.addAll(entityValidator.validate(entity)));
    if (!problems.isEmpty()) {
      log.warn("Entity [{}] has problems: {}", entity.toString(),
          StringUtils.join(problems));
      return false;
    }
    return true;
  }
}
