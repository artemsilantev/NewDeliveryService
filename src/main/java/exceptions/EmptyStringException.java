package exceptions;

public class EmptyStringException extends Exception{


    @Override
    public String toString() {
        return "Text is empty";
    }
}
