package by.schepov.motordepot.command.impl;

import by.schepov.motordepot.command.RepositoryAction;
import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.exception.service.RequestServiceException;
import by.schepov.motordepot.parameter.JSPParameter;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.SessionAttribute;
import by.schepov.motordepot.service.request.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

public class RejectRequest extends RepositoryAction {
    private static final Logger LOGGER = LogManager.getLogger(SubmitOrder.class);

    //todo create ServiceFactory
    private final RequestService requestService = serviceFactory.createRequestService();
    private static final String BUNDLE_NAME = "locale";

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        try{
            int requestId = Integer.parseInt(request.getParameter(JSPParameter.REQUEST_ID.getName()));
            Request foundRequest = requestService.getRequestById(requestId);
            if(foundRequest == null) {
                LOGGER.warn("Request hasn't been found(id=" + requestId + ")");
                setMessage(request.getSession(), MessageKey.UNEXPECTED_ERROR);
                return Page.ERROR;
            }
            requestService.deleteRequest(foundRequest);
            Set<Request> requests = requestService.getAllRequests();
            request.getSession().setAttribute(SessionAttribute.REQUESTS.getName(), requests);
            return Page.MANAGEMENT_REQUESTS;
        } catch (RequestServiceException e) {
            LOGGER.warn(e);
            if(e.hasMessageBundleKey()){
                setMessage(request.getSession(), e.getMessageBundleKey());
            }
            return Page.ERROR;
        }
    }

    private void setMessage(HttpSession session, MessageKey messageKey){
        setMessage(session, messageKey, BUNDLE_NAME);
    }

}
