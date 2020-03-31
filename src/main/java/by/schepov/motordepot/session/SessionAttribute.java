package by.schepov.motordepot.session;

public enum SessionAttribute {
    ROLE("role"),
    LAST_REQUEST("last_request"),
    LOCALE("locale"),
    LAST_PAGE("last_page"),
    USER("user");

    private String name;

    SessionAttribute(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
