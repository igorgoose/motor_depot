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
    LOAD_CAPACITY("load_capacity"),
    DEPARTURE_TIME("departure_time"),
    DEPARTURE_LOCATION("departure_location"),
    ARRIVAL_TIME("arrival_time"),
    ARRIVAL_LOCATION("arrival_location"),
    REGISTRATION_NUMBER("registration_number"),
    PASSENGER_CAPACITY("passenger_capacity"),
    MODEL_ID("model_id"),
    BRAND_ID("brand_id"),
    NAME("name"),
    NAME_ID("name_id"),
    BRAND("brand"),
    MODEL("model"),
    DRIVER_ID("driver_id"),
    STATUS("status"),
    USER_EMAIL("user_email"),
    USER_LOGIN("user_login"),
    USER_PASSWORD("user_password"),
    USER_ROLE("user_role"),
    USER_BLOCKED("user_blocked"),
    DRIVER_EMAIL("driver_email"),
    DRIVER_LOGIN("driver_login"),
    DRIVER_PASSWORD("driver_password"),
    DRIVER_ROLE("driver_role"),
    DRIVER_BLOCKED("driver_blocked"),
    CAR_ID("car_id"),
    MODEL_NAME("model_name"),
    BRAND_NAME("brand_name"),
    CAR_NAME_ID("car_name_id"),
    CAR_STATUS("car_status"),
    IS_COMPLETE("is_complete"),
    USER_STATUS("user_status"),
    DRIVER_STATUS("driver_status");

    private String name;

    Column(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
