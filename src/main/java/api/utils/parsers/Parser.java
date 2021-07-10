package api.utils.parsers;

import org.modelmapper.Converters;

import java.lang.reflect.Type;
import java.util.Collection;

public interface Parser {
    Collection<?> deserializeCollection(String textToParse, Type type);
    <E> String serializeEntity(E entity);
}
