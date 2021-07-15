package configs;

import lombok.Builder;
import lombok.Getter;
import mappers.Mapper;
import model.BaseEntity;
import validators.ObjectValidator;
import validators.TextValidator;
import filemanagers.FileManager;

import java.util.Collection;

@Getter
@Builder
public class DataStorageConfiguration{
    private final Mapper mapper;
    private final FileManager fileManager;
    private final String pathToFile;
    private final Collection<TextValidator> textValidators;
    private final Collection<ObjectValidator> objectValidators;
}
