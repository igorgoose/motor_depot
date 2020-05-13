package by.schepov.motordepot.parameter;

public enum SessionAttribute {

    //ROLE_ID("role_id"),
    USERNAME("username"),
    REQUESTS("requests"),
    CARS("cars"),
    REQUEST("request"),
    MESSAGE("message"),
    ROLE("role"),
    LOCALE("locale"),
    THAT_USER("that_user"),
    USER("user");

    private final String name;

    SessionAttribute(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
