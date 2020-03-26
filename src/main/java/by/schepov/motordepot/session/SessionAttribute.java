package by.schepov.motordepot.session;

public enum SessionAttribute {
    USER("user");

    private String name;

    SessionAttribute(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
