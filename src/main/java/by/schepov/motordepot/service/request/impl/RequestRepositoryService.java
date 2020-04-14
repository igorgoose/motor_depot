package by.schepov.motordepot.service.request.impl;

import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.repository.impl.request.RequestRepository;
import by.schepov.motordepot.service.RepositoryService;
import by.schepov.motordepot.service.request.RequestService;
import by.schepov.motordepot.specification.impl.request.FindRequestByUserIdSpecification;
import by.schepov.motordepot.specification.impl.request.GetAllRequestsSpecification;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public Set<Request> findRequestsByUserId(int id) throws RequestServiceException {
        try {
            return repository.execute(new FindRequestByUserIdSpecification(id));
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new RequestServiceException(e);
        }
    }

    @Override
    public Set<Request> getAllRequests() throws ServiceException {
        try {
            return repository.execute(new GetAllRequestsSpecification());
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Set<Request> getRequestsByUserId(int id) throws ServiceException {
        try {
            return repository.execute(new FindRequestByUserIdSpecification(id));
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new ServiceException(e);
        }
    }

}
