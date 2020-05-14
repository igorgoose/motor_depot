package by.schepov.motordepot.command;

import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.RequestAttribute;
import by.schepov.motordepot.parameter.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This interface provides methods to process {@link HttpServletRequest requests}.
 *
 * @author Igor Schepov
 *
 * @see Command
 *
 */
interface Executable {


    /**
     * The method that processes {@link HttpServletRequest requests}.
     *
     * @param request
     * @param response
     * @return the page that needs to be shown to the user after processing the request
     */
    Page execute(HttpServletRequest request, HttpServletResponse response);

    /**
     * Adds a message to the request in order to notify the user if
     * needed.
     *
     * @param request the request to add a message to
     * @param messageKey the key for the message in a bundle
     * @param bundleName the name of the bundle
     */
    default void setMessage(HttpServletRequest request, MessageKey messageKey, String bundleName){
        Locale locale = new Locale((String) request.getSession().getAttribute(SessionAttribute.LOCALE.getName()));
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
        request.setAttribute(RequestAttribute.MESSAGE.getName(), bundle.getString(messageKey.getValue()));
    }

    /**
     * Adds a message to the session in order to notify the user if
     * needed.
     *
     * @param session the session to add a message to
     * @param messageKey the key for the message in a bundle
     * @param bundleName he name of the bundle
     */
    default void setMessage(HttpSession session, MessageKey messageKey, String bundleName){
        Locale locale = new Locale((String) session.getAttribute(SessionAttribute.LOCALE.getName()));
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
        session.setAttribute(RequestAttribute.MESSAGE.getName(), bundle.getString(messageKey.getValue()));
    }

}
