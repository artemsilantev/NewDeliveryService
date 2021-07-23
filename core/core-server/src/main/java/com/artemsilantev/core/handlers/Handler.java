package com.artemsilantev.core.handlers;

public interface Handler<T, S> {

  T getHandler(S source);
}
