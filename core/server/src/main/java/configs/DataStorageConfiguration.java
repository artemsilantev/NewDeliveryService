package configs;

import filemanagers.FileManager;
import handlers.Handler;
import java.util.Collection;
import lombok.Getter;
import mappers.Mapper;
import model.BaseEntity;
import validators.Validator;

@Getter
public class DataStorageConfiguration<E extends BaseEntity> {

  private final Handler<Mapper<E, String>, String> mapperHandler;
  private final FileManager fileManager;
  private final String pathToFile;
  private final Collection<Validator<String>> textValidators;
  private final Collection<Validator<E>> entityValidators;

  private DataStorageConfiguration(Builder<E> builder) {
    this.mapperHandler = builder.mapperHandler;
    this.fileManager = builder.fileManager;
    this.pathToFile = builder.pathToFile;
    this.textValidators = builder.textValidators;
    this.entityValidators = builder.entityValidators;
  }

  public static <E extends BaseEntity> Builder<E> builder() {
    return new Builder<>();
  }

  public static class Builder<E extends BaseEntity> {

    private Handler<Mapper<E, String>, String> mapperHandler;
    private FileManager fileManager;
    private String pathToFile;
    private Collection<Validator<String>> textValidators;
    private Collection<Validator<E>> entityValidators;


    public Builder<E> setMapperHandler(Handler<Mapper<E, String>, String> mapperHandler) {
      this.mapperHandler = mapperHandler;
      return this;
    }

    public Builder<E> setFileManager(FileManager fileManager) {
      this.fileManager = fileManager;
      return this;
    }

    public Builder<E> setPathToFile(String pathToFile) {
      this.pathToFile = pathToFile;
      return this;
    }

    public Builder<E> setTextValidators(Collection<Validator<String>> textValidators) {
      this.textValidators = textValidators;
      return this;
    }

    public Builder<E> setEntityValidators(Collection<Validator<E>> entityValidators) {
      this.entityValidators = entityValidators;
      return this;
    }

    public DataStorageConfiguration<E> build() {
      return new DataStorageConfiguration<>(this);
    }

  }
}
