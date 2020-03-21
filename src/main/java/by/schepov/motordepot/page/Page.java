package by.schepov.motordepot.page;

public enum Page {

    HOME("index.jsp");

    private String name;

    Page(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
