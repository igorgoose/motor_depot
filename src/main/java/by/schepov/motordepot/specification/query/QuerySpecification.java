package by.schepov.motordepot.specification.query;

import by.schepov.motordepot.exception.specification.SpecificationException;

import java.util.Set;

public interface QuerySpecification<T> {
    Set<T> execute() throws SpecificationException;
}
