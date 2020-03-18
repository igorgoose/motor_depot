package by.schepov.motordepot.specification;

import java.util.Set;

public interface Specification<T> {
    Set<T> execute();
}
