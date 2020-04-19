package by.schepov.motordepot.repository;

import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.specification.SpecificationException;
import by.schepov.motordepot.specification.query.QuerySpecification;
import by.schepov.motordepot.specification.update.UpdateSpecification;

import java.util.Set;

public interface Repository<T> {
    void insert(T item) throws RepositoryException;
    void delete(T item) throws RepositoryException;
    default Set<T> executeQuery(QuerySpecification<T> querySpecification) throws RepositoryException {
        try {
            return querySpecification.execute();
        } catch (SpecificationException e) {
            throw new RepositoryException(e);
        }
    }
    default void executeUpdate(UpdateSpecification<T> updateSpecification) throws RepositoryException {
        try {
            updateSpecification.execute();
        } catch (SpecificationException e) {
            throw new RepositoryException(e);
        }
    }
}
