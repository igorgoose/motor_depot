package by.schepov.motordepot.validator;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.UserValidatorException;

public class UserValidator {

    private static final String USERNAME_REGEX = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)(\\.(.+))+$";
    private static final String PASSWORD_REGEX = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*";

    private UserValidator() {
    }

    public static void validateUser(User user) throws UserValidatorException {
        if (user.getUsername() == null || user.getEmail() == null || user.getPassword() == null ||
                !user.getUsername().matches(USERNAME_REGEX) ||
                !user.getEmail().matches(EMAIL_REGEX) ||
                !user.getPassword().matches(PASSWORD_REGEX)) {
            throw new UserValidatorException("User data is invalid:" + user);
        }
    }

    public static void validateUsernameAndPassword(User user) throws UserValidatorException {
        if (user.getUsername() == null || user.getPassword() == null ||
                !user.getUsername().matches(USERNAME_REGEX) ||
                !user.getPassword().matches(PASSWORD_REGEX)) {
            throw new UserValidatorException("User data is invalid:" + user);
        }
    }

    public static void validatePasswordRepetition(String password, String repeatedPassword) throws UserValidatorException {
        if(password == null || !password.equals(repeatedPassword)){
            throw new UserValidatorException("Repeated password is incorrect");
        }
    }


}
