package by.schepov.motordepot.command;

import by.schepov.motordepot.jsp.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Language implements Executable {

    private Page lastPage;

    Language(){

    }

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

//    public void setLastPage(Page ){
//        lastPage = lastPage
//    }
}
