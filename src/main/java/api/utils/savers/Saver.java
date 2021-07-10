package api.utils.savers;

public interface Saver {
   <E> void save(E entityToSave, String path );
}
