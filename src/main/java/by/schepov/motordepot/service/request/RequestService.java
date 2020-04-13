package by.schepov.motordepot.service.request;

import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.exception.service.RequestServiceException;
import com.google.protobuf.ServiceException;

import java.util.Set;

public interface RequestService {
    Set<Request> findRequestsByUserId(int id) throws RequestServiceException;
    Set<Request> getAllRequests() throws ServiceException;
}