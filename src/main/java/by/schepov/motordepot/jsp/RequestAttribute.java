package by.schepov.motordepot.jsp;

import by.schepov.motordepot.exception.InvalidParameterException;

public enum RequestAttribute {

    USER("user"),
    MANAGEMENT_REQUEST("management_request"),
    ROLE("role"),
    USERNAME("username"),
    REQUESTS("requests"),
    USERS("users"),
    CARS("cars"),
    ORDERS("orders");

    private String value;

    RequestAttribute(String value){
        this.value = value;
    }

    public String getName() {
        return value;
    }

    public static RequestAttribute getJSPParameterByValue(String value){
        for (RequestAttribute item: RequestAttribute.values()) {
            if(item.value.equals(value)){
                return item;
            }
        }
        throw new InvalidParameterException("Parameter passed: " + value);
    }
}
