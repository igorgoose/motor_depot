package by.schepov.motordepot.command;

import by.schepov.motordepot.jsp.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Language implements Executable {

    private Page lastPage;

    Language(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        return null;
    }

//    public void setLastPage(Page ){
//        lastPage = lastPage
//    }
}
