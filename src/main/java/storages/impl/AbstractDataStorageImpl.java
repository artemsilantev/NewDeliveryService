package storages.impl;

import configs.DataStorageConfiguration;
import exceptions.AccessFileException;
import exceptions.LoadDataException;
import mappers.Mapper;
import model.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import storages.AbstractDataStorage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractDataStorageImpl<E extends BaseEntity>
        implements AbstractDataStorage<E> {

    private Long entityIdSequence;
    private Collection<E> entities;
    DataStorageConfiguration configuration;


    protected AbstractDataStorageImpl(DataStorageConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public synchronized Collection<E> getEntities() {
        if (entities == null) {
            entities = load();
            entityIdSequence = entities.stream().mapToLong(E::getId).max().orElse(0L);
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
        Mapper<E, String> mapper = configuration.getMapper();
        var fileManager = configuration.getFileManager();
        var pathToFile = configuration.getPathToFile();
        var lines = entities.stream().map(mapper::toSource).collect(Collectors.toList());
        try {
            fileManager.write(pathToFile, lines);
        } catch (IOException ioException) {
            System.out.printf("Couldn't save data by path \"%s\" in %s %n", pathToFile, this.getClass().getSimpleName());
            throw new AccessFileException(pathToFile, ioException.getMessage());
        }
    }

    protected Collection<E> load() {
        var fileManager = configuration.getFileManager();
        var pathToFile = configuration.getPathToFile();
        Mapper<E, String> mapper = configuration.getMapper();
        try {
            return fileManager.read(pathToFile).stream()
                    .filter(this::validateText)
                    .map(entity -> {
                        try {
                            return mapper.toTarget(entity);
                        } catch (Exception exception) {
                            System.out.printf("Entity [%s] has problem with parsing:%s %n",
                                    entity, exception.getMessage());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .filter(this::validateEntity)
                    .collect(Collectors.toList());
        } catch (IOException ioException) {
            System.out.printf("Problem with loading data (path: \"%s\")%n", pathToFile);
            throw new LoadDataException(ioException.getMessage());
        }
    }

    protected boolean validateText(String text) {
        var problems = new ArrayList<String>();
        configuration.getTextValidators().forEach(textValidator -> problems.addAll(textValidator.validate(text)));
        if (!problems.isEmpty()) {
            System.out.printf("Text [%s] has problems: %s %n", text,
                    StringUtils.join(problems));
            return false;
        }
        return true;
    }

    protected boolean validateEntity(E entity) {
        var problems = new ArrayList<String>();
        configuration.getObjectValidators().forEach(objectValidator -> problems.addAll(objectValidator.validate(entity)));
        if (!problems.isEmpty()) {
            System.out.printf("Entity [%s] has problems: %s %n", entity.toString(),
                    StringUtils.join(null, ',', problems));
            return false;
        }
        return true;
    }
}
