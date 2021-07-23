package com.artemsilantev.core.mappers;

import java.util.Collection;

public interface Mapper<T, S> {

  T toTarget(S source);

  S toSource(T target);

  Collection<T> toTargetCollection(Collection<S> sourceCollection);

  Collection<S> toSourceCollection(Collection<T> targetCollection);
}
