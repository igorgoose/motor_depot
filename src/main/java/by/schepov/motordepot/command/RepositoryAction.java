package by.schepov.motordepot.command;

import by.schepov.motordepot.factory.service.ServiceFactory;
import by.schepov.motordepot.factory.service.impl.RepositoryServiceFactory;

public abstract class RepositoryAction implements Action {

    protected final ServiceFactory serviceFactory = RepositoryServiceFactory.getInstance();

}
