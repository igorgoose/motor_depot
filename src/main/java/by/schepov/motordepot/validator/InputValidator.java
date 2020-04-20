package by.schepov.motordepot.validator;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.validator.InputValidatorException;

import java.util.regex.Pattern;

public class InputValidator {

    private static final String USERNAME_REGEX = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)(\\.(.+))+$";
    private static final String PASSWORD_REGEX = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*";
    private static final Pattern usernamePattern = Pattern.compile(USERNAME_REGEX);
    private static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);

    private InputValidator(){

    }

    public static void validateUserFields(User user) throws InputValidatorException {
        if(!passwordPattern.matcher(user.getPassword()).matches()){
            throw new InputValidatorException("Invalid password");
        }
        if(!emailPattern.matcher(user.getEmail()).matches()){
            throw new InputValidatorException("Invalid email");
        }
        if(!usernamePattern.matcher(user.getUsername()).matches()){
            throw new InputValidatorException("Invalid username");
        }
    }


}
