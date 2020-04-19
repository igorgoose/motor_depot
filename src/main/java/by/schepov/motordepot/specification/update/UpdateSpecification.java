package by.schepov.motordepot.specification.update;

import by.schepov.motordepot.exception.specification.SpecificationException;

public interface UpdateSpecification<T> {
    void execute() throws SpecificationException;
}
