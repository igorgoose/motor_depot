package by.schepov.motordepot.validator;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.entity.UserStatus;
import by.schepov.motordepot.exception.validator.*;

public class UserValidator {

    private static final String USERNAME_REGEX = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)(\\.(.+))+$";
    private static final String PASSWORD_REGEX = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*";

    private UserValidator() {
    }

    public static void validateUser(User user) throws InvalidUserEmailException, InvalidUsernameOrPasswordException {
        if(user.getEmail() == null || !user.getEmail().matches(EMAIL_REGEX)){
            throw new InvalidUserEmailException("User data is invalid:" + user);
        }
        if (user.getUsername() == null || user.getPassword() == null ||
                !user.getUsername().matches(USERNAME_REGEX) ||
                !user.getPassword().matches(PASSWORD_REGEX)) {
            throw new InvalidUsernameOrPasswordException("User data is invalid:" + user);
        }
    }

    public static void validateUsernameAndPassword(User user) throws InvalidUsernameOrPasswordException {
        if (user.getUsername() == null || user.getPassword() == null ||
                !user.getUsername().matches(USERNAME_REGEX) ||
                !user.getPassword().matches(PASSWORD_REGEX)) {
            throw new InvalidUsernameOrPasswordException("User data is invalid:" + user);
        }
    }

    public static void validatePasswordRepetition(String password, String repeatedPassword) throws PasswordRepetitionException {
        if(password == null || !password.equals(repeatedPassword)){
            throw new PasswordRepetitionException("Repeated password is incorrect(" + password + ", " + repeatedPassword + ")");
        }
    }

    public static void validateStatus(User user) throws UserStatusIsNullOrBlockedException {
        if(user.getStatus() == null || user.getStatus() == UserStatus.BLOCKED){
            throw new UserStatusIsNullOrBlockedException("User(id=" + user.getId() + ") has status=" + user.getStatus());
        }
    }

}
