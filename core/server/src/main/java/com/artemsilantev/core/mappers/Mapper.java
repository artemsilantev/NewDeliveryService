package com.artemsilantev.core.mappers;

public interface Mapper<T, S> {

  T toTarget(S source);

  S toSource(T target);
}
