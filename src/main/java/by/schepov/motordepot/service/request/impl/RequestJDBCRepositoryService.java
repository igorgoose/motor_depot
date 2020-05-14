package by.schepov.motordepot.service.request.impl;

import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.exception.validator.request.*;
import by.schepov.motordepot.factory.repository.impl.JDBCRepositoryFactory;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.repository.Repository;
import by.schepov.motordepot.service.RepositoryService;
import by.schepov.motordepot.service.request.RequestService;
import by.schepov.motordepot.specification.query.impl.request.FindRequestByIdQuerySpecification;
import by.schepov.motordepot.specification.query.impl.request.FindRequestByUserIdQuerySpecification;
import by.schepov.motordepot.specification.query.impl.request.GetAllRequestsQuerySpecification;
import by.schepov.motordepot.validator.RequestValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Set;

public class RequestJDBCRepositoryService extends RepositoryService<Request> implements RequestService {

    private static final Logger LOGGER = LogManager.getLogger(RequestJDBCRepositoryService.class);

    private final Repository<Request> repository;

    private RequestJDBCRepositoryService() {
        super(JDBCRepositoryFactory.getInstance());
        repository = repositoryFactory.createRequestRepository();
    }

    public static class InstanceHolder{
        public static final RequestJDBCRepositoryService INSTANCE = new RequestJDBCRepositoryService();
    }

    public static RequestJDBCRepositoryService getInstance(){
        return InstanceHolder.INSTANCE;
    }

    @Override
    public void insertRequest(Request request) throws RequestServiceException {
        try {
            RequestValidator.validateRequest(request);
            repository.insert(request);
        } catch (RepositoryException | NullUserException e) {
            LOGGER.warn(e);
            RequestServiceException ex = new RequestServiceException(e);
            ex.setMessageBundleKey(MessageKey.UNEXPECTED_ERROR);
            throw ex;
        } catch (EmptyArrivalLocationException e) {
            LOGGER.warn(e);
            RequestServiceException ex = new RequestServiceException(e);
            ex.setMessageBundleKey(MessageKey.EMPTY_ARRIVAL_LOCATION);
            throw ex;
        } catch (NegativePassengerQuantityException e) {
            LOGGER.warn(e);
            RequestServiceException ex = new RequestServiceException(e);
            ex.setMessageBundleKey(MessageKey.NEGATIVE_PASSENGER_QUANTITY);
            throw ex;
        } catch (EmptyDepartureLocationException e) {
            LOGGER.warn(e);
            RequestServiceException ex = new RequestServiceException(e);
            ex.setMessageBundleKey(MessageKey.EMPTY_DEPARTURE_LOCATION);
            throw ex;
        } catch (NegativeLoadException e) {
            LOGGER.warn(e);
            RequestServiceException ex = new RequestServiceException(e);
            ex.setMessageBundleKey(MessageKey.NEGATIVE_LOAD);
            throw ex;
        }
    }

    @Override
    public void deleteRequest(Request request) throws RequestServiceException {
        try {
            repository.delete(request);
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            RequestServiceException ex = new RequestServiceException(e);
            ex.setMessageBundleKey(MessageKey.UNEXPECTED_ERROR);
            throw ex;
        }
    }

    @Override
    public Set<Request> getAllRequests() throws RequestServiceException {
        try {
            return repository.executeQuery(new GetAllRequestsQuerySpecification());
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            RequestServiceException ex = new RequestServiceException(e);
            ex.setMessageBundleKey(MessageKey.UNEXPECTED_ERROR);
            throw ex;
        }
    }

    @Override
    public Set<Request> getRequestsByUserId(int id) throws RequestServiceException {
        try {
            return repository.executeQuery(new FindRequestByUserIdQuerySpecification(id));
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            RequestServiceException ex = new RequestServiceException(e);
            ex.setMessageBundleKey(MessageKey.UNEXPECTED_ERROR);
            throw ex;
        }
    }

    @Override
    public Request getRequestById(int requestId) throws RequestServiceException {
        try {
            Set<Request> requests = repository.executeQuery(new FindRequestByIdQuerySpecification(requestId));
            Iterator<Request> iterator = requests.iterator();
            return iterator.hasNext() ? iterator.next() : null;
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            RequestServiceException ex = new RequestServiceException(e);
            ex.setMessageBundleKey(MessageKey.UNEXPECTED_ERROR);
            throw ex;
        }
    }

}
