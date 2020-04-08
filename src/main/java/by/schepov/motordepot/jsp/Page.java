package by.schepov.motordepot.jsp;

public enum Page {

    MANAGEMENT("/jsp/management.jsp"),
    HOME("index.jsp"),
    AUTHORIZE("/jsp/authorize.jsp"),
    SIGN_UP("/jsp/signup.jsp"),
    PROFILE("/jsp/profile.jsp"),
    ERROR("/jsp/error.jsp");

    private String name;

    Page(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
