package by.schepov.motordepot.command;

import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.parameter.Page;
import by.schepov.motordepot.parameter.RequestAttribute;
import by.schepov.motordepot.session.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

interface Executable {
    Page execute(HttpServletRequest request, HttpServletResponse response);
    default void setMessage(HttpServletRequest request, MessageKey messageKey, String BUNDLE_NAME){
        Locale locale = new Locale((String) request.getSession().getAttribute(SessionAttribute.LOCALE.getName()));
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        request.setAttribute(RequestAttribute.MESSAGE.getName(), bundle.getString(messageKey.getValue()));
    }
}
