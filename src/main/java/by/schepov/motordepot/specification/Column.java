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
    ARRIVAL_LOCATION("arrival_location"),
    REGISTRATION_NUMBER("registration_number"),
    PASSENGER_CAPACITY("passenger_capacity"),
    LOAD_CAPACITY("load_capacity"),
    MODEL_ID("model_id"),
    BRAND_ID("brand_id"),
    NAME("name"),
    NAME_ID("name_id"),
    BRAND("brand"),
    MODEL("model"),
    DRIVER_ID("driver_id"),
    STATUS("status");

    private String name;

    Column(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
