package by.schepov.motordepot.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class CopyrightTag extends TagSupport {

    private static final String MESSAGE = "Copyright \u00a9 2020 MotorDepot Online. All rights reserved.";

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write(MESSAGE);
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}