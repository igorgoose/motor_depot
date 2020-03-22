package by.schepov.motordepot.specification;

import by.schepov.motordepot.exception.specification.SpecificationException;

import java.util.Set;

public interface Specification<T> {
    Set<T> execute() throws SpecificationException;
}
