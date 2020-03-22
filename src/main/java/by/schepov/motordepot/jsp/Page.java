package by.schepov.motordepot.jsp;

public enum Page {

    HOME("index.jsp"),
    WELCOME("/jsp/welcome.jsp");

    private String name;

    Page(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
