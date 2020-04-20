package by.schepov.motordepot.service.request;

import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.exception.service.RequestServiceException;

import java.util.Set;

public interface RequestService {
    void insertRequest(Request request) throws RequestServiceException;
    void deleteRequest(Request request) throws RequestServiceException;
    Set<Request> getAllRequests() throws RequestServiceException;
    Set<Request> getRequestsByUserId(int id) throws RequestServiceException;
    Request getRequestById(int requestId) throws RequestServiceException;
}
