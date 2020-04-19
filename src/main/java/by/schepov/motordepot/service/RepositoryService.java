package by.schepov.motordepot.service;

import by.schepov.motordepot.repository.Repository;

public abstract class RepositoryService<T> {

    protected Repository<T> repository;

    public RepositoryService(Repository<T> repository){
        this.repository = repository;
    }

}
