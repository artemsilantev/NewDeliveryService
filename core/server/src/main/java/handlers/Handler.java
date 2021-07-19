package handlers;

public interface Handler<T, S> {

  T getHandler(S source);
}
