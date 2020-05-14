package by.schepov.motordepot.service;

import by.schepov.motordepot.factory.repository.RepositoryFactory;

public abstract class RepositoryService<T> {

    protected RepositoryFactory repositoryFactory;

    public RepositoryService(RepositoryFactory repositoryFactory){
        this.repositoryFactory = repositoryFactory;
    }

}
