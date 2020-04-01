package by.schepov.motordepot.service;

import by.schepov.motordepot.repository.Repository;

public abstract class Service<T> {

    protected Repository<T> repository;

    public Service(Repository<T> repository){
        this.repository = repository;
    }

}
