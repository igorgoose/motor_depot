package by.schepov.motordepot.repository;

import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.specification.Specification;

import java.util.Set;

public interface Repository<T> {
    void insert(T item) throws RepositoryException;
    default Set<T> execute(Specification<T> query){
        return query.execute();
    }
}
