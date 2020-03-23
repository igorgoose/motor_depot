package by.schepov.motordepot.validator;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.validator.InputValidatorException;

import java.util.regex.Pattern;

public class InputValidator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);

    private InputValidator(){

    }

    public static void validateEmailAndPassword(User user) throws InputValidatorException {
        if(!passwordPattern.matcher(user.getPassword()).matches()){
            throw new InputValidatorException("Invalid password");
        }
        if(user.getEmail() != null && !emailPattern.matcher(user.getEmail()).matches()){
            throw new InputValidatorException("Invalid email");
        }
    }


}
