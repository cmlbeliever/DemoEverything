package com.cml.framework.worksummary.diff.comparator;

public interface DiffComparator<T> {
    String generateKey(T t);

    boolean equals(T src,T std);
}
