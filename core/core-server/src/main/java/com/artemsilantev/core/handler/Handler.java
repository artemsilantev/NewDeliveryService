package com.artemsilantev.core.handler;

public interface Handler<T, S> {

  T getHandler(S source);
}
