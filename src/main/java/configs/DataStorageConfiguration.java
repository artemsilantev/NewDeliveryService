package configs;

import lombok.Builder;
import lombok.Getter;
import mappers.Mapper;
import filemanagers.FileManager;
import model.BaseEntity;
import validators.Validator;

import java.util.Collection;

@Getter
@Builder
public class DataStorageConfiguration<E extends BaseEntity>{
    private final Mapper mapper;
    private final FileManager fileManager;
    private final String pathToFile;
    private final Collection<Validator<String>> textValidators;
    private final Collection<Validator<E>> entityValidators;
}
