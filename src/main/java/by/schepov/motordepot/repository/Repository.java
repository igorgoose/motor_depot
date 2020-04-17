package by.schepov.motordepot.repository;

import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.specification.SpecificationException;
import by.schepov.motordepot.specification.Specification;

import java.util.Set;

public interface Repository<T> {
    void insert(T item) throws RepositoryException;
    void delete(T item) throws RepositoryException;
    default Set<T> execute(Specification<T> query) throws RepositoryException {
        try {
            return query.execute();
        } catch (SpecificationException e) {
            throw new RepositoryException(e);
        }
    }
}
