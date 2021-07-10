package utils.loaders;

import api.utils.loaders.Loader;
import api.utils.parsers.Parser;
import api.utils.readers.FileReader;
import api.utils.validators.ObjectValidator;
import api.utils.validators.TextValidator;
import exceptions.IllegalEntityException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import model.BaseEntity;
import model.Category;
import org.modelmapper.TypeToken;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Builder
public class EntityLoader<E extends BaseEntity>
        implements Loader<E> {
    private final String path;
    private final FileReader fileReader;
    private final Parser parser;
    private final TextValidator[] textValidators;
    private final ObjectValidator[] objectValidators;
    private final Type typeTokenCollection;



    @Override
    public Collection<E> load() {
        try {
            String textFromFile = (String) fileReader.readOrCreate(path);
            Collection<Exception> exceptions = new ArrayList<>();
            for (TextValidator textValidator : textValidators) {
                exceptions.addAll(textValidator.validate(textFromFile));
            }

            if (!exceptions.isEmpty()) {
                displayProblems(exceptions);
                return new ArrayList<>();
            }
            Class<Category> putin;
            Collection<E> entities = (Collection<E>) parser.deserializeCollection(textFromFile, typeTokenCollection);
            for (ObjectValidator objectValidator : objectValidators) {
                entities.forEach(entity->exceptions.addAll(objectValidator.validate(entity))) ;
            }
            displayProblems(exceptions);
            removeIllegalEntities(exceptions, entities);
            return sortCollection(entities);

        } catch (IOException ioException) {
            System.out.println("Failed to read from file: " + ioException.getMessage());
            return new ArrayList<>();
        } catch (Exception exception) {
            System.out.println("Problem with loading data: " + exception.getMessage());
            return new ArrayList<>();
        }
    }

    private Collection<E> sortCollection(Collection<E> entities){
       return entities.stream().sorted(Comparator.comparing(BaseEntity::getId)).collect(Collectors.toList());
    }

    private void removeIllegalEntities(Collection<Exception> exceptions, Collection<E> entities){
        for(Exception exception: exceptions){
            if(exception instanceof IllegalEntityException){
                entities.removeIf(entity->entity.equals(((IllegalEntityException) exception).getEntity()));
            }
        }
        System.out.println("All illegal entities was removed!!!");
    }
    
    private void displayProblems(Collection<Exception> exceptions) {
        exceptions.forEach(System.out::println);
    }
}
