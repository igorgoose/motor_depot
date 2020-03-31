package by.schepov.motordepot.specification;

public enum Column {

    ID("id"),
    LOGIN("login"),
    PASSWORD("password"),
    ROLE_ID("role_id"),
    EMAIL("email"),
    IS_BLOCKED("is_blocked"),
    USER_ID("user_id"),
    ROUTE_ID("route_id"),
    ROLE("role"),
    PASSENGERS_QUANTITY("passengers_quantity"),
    LOAD_KG("load_kg"),
    DEPARTURE_TIME("departure_time"),
    DEPARTURE_LOCATION("departure_location"),
    ARRIVAL_TIME("arrival_time"),
    ARRIVAL_LOCATION("arrival_location");

    private String name;

    Column(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
