package by.schepov.motordepot.service.impl;

import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.repository.impl.request.RequestRepository;
import by.schepov.motordepot.service.Service;
import by.schepov.motordepot.specification.impl.request.FindRequestByUserIdSpecification;
import by.schepov.motordepot.specification.impl.request.GetAllRequestsSpecification;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class RequestService extends Service<Request> {

    private static final Logger LOGGER = LogManager.getLogger(RequestService.class);

    private RequestService() {
        super(RequestRepository.INSTANCE);
    }

    public static class InstanceHolder{
        public static final RequestService INSTANCE = new RequestService();
    }

    public static RequestService getInstance(){
        return InstanceHolder.INSTANCE;
    }

    public Set<Request> findRequestsByUserId(int id) throws RequestServiceException {
        try {
            return repository.execute(new FindRequestByUserIdSpecification(id));
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new RequestServiceException(e);
        }
    }

    public Set<Request> getAllRequests() throws ServiceException {
        try {
            return repository.execute(new GetAllRequestsSpecification());
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new ServiceException(e);
        }
    }
}
