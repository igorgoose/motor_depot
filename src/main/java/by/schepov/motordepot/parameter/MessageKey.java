package by.schepov.motordepot.parameter;

public enum MessageKey {

    YOU_ARE_BLOCKED("message.you_are_blocked"),
    DRIVER_IS_BUSY_OR_BLOCKED("message.driver_is_busy_or_blocked"),
    CAR_IS_BUSY("message.busy_car"),
    UNEXPECTED_ERROR("message.unexpected_error"),
    WRONG_USERNAME_OR_PASSWORD("message.wrong_username_or_password"),
    USERNAME_TAKEN("message.username_taken"),
    PASSWORD_REPEATED_INCORRECTLY("message.password_repeated_incorrectly"),
    INCORRECT_EMAIL("message.incorrect_email"),
    INVALID_REQUEST_DATA("message.invalid_request_data"),
    ACCESS_DENIED("message.access_denied"),
    EMPTY_ARRIVAL_LOCATION("message.empty_arrival_location"),
    NEGATIVE_PASSENGER_QUANTITY("message.negative_passenger_quantity"),
    EMPTY_DEPARTURE_LOCATION("message.empty_departure_location"),
    NEGATIVE_LOAD("message.negative_load"),
    REQUEST_CREATED("message.request_created"),
    NO_SUITING_CARS("message.no_suiting_cars");

    private final String value;

    MessageKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
