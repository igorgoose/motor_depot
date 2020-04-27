package by.schepov.motordepot.service.request.impl;

import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.repository.impl.request.RequestRepository;
import by.schepov.motordepot.service.RepositoryService;
import by.schepov.motordepot.service.request.RequestService;
import by.schepov.motordepot.specification.query.impl.request.FindRequestByIdQuerySpecification;
import by.schepov.motordepot.specification.query.impl.request.FindRequestByUserIdQuerySpecification;
import by.schepov.motordepot.specification.query.impl.request.GetAllRequestsQuerySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Set;

public class RequestRepositoryService extends RepositoryService<Request> implements RequestService {

    private static final Logger LOGGER = LogManager.getLogger(RequestRepositoryService.class);

    private RequestRepositoryService() {
        super(RequestRepository.INSTANCE);
    }

    public static class InstanceHolder{
        public static final RequestRepositoryService INSTANCE = new RequestRepositoryService();
    }

    public static RequestRepositoryService getInstance(){
        return InstanceHolder.INSTANCE;
    }

    @Override
    public void insertRequest(Request request) throws RequestServiceException {
        try {
            repository.insert(request);
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new RequestServiceException(e);
        }
    }

    @Override
    public void deleteRequest(Request request) throws RequestServiceException {
        try {
            repository.delete(request);
        } catch (RepositoryException e) {
            throw new RequestServiceException(e);
        }
    }

    @Override
    public Set<Request> getAllRequests() throws RequestServiceException {
        try {
            return repository.executeQuery(new GetAllRequestsQuerySpecification());
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new RequestServiceException(e);
        }
    }

    @Override
    public Set<Request> getRequestsByUserId(int id) throws RequestServiceException {
        try {
            return repository.executeQuery(new FindRequestByUserIdQuerySpecification(id));
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new RequestServiceException(e);
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
            throw new RequestServiceException(e);
        }
    }

}
