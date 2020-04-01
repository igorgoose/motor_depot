package by.schepov.motordepot.jsp;

import by.schepov.motordepot.exception.InvalidParameterException;

public enum JSPParameter {
    COMMAND("command"),
    LANGUAGE("language"),
    USERNAME("username"),
    PASSWORD("password"),
    EMAIL("email"),
    ADDRESS("address"),
    REPEAT_PASSWORD("repeat_password");

    private String value;

    JSPParameter(String value){
        this.value = value;
    }

    public String getName() {
        return value;
    }

    public static JSPParameter getJSPParameterByValue(String value){
        for (JSPParameter item: JSPParameter.values()) {
            if(item.value.equals(value)){
                return item;
            }
        }
        throw new InvalidParameterException("Parameter passed: " + value);
    }

}
