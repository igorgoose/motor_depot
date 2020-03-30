package by.schepov.motordepot.session;

public enum SessionAttribute {
    LOCALE("locale"),
    CURRENT_PAGE("current_page"),
    USER("user");

    private String name;

    SessionAttribute(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
