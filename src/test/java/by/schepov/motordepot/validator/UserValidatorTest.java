package by.schepov.motordepot.validator;

import by.schepov.motordepot.entity.Role;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.entity.UserStatus;
import by.schepov.motordepot.exception.validator.user.InvalidUserEmailException;
import by.schepov.motordepot.exception.validator.user.InvalidUsernameOrPasswordException;
import by.schepov.motordepot.exception.validator.user.PasswordRepetitionException;
import by.schepov.motordepot.exception.validator.user.UserStatusIsNullOrBlockedException;
import org.junit.Before;
import org.junit.Test;

public class UserValidatorTest {

    private User user;

    @Before
    public void initUser(){
        user = new User();
        user.setId(1);
        user.setUsername("Username");
        user.setEmail("email@mail.com");
        user.setRole(Role.USER);
        user.setStatus(UserStatus.ACTIVE);
        user.setPassword("Password123");
    }

    @Test
    public void validateUserPositive() throws InvalidUsernameOrPasswordException, InvalidUserEmailException {
        UserValidator.validateUser(user);
    }

    @Test(expected = InvalidUserEmailException.class)
    public void validateUserNullEmail1() throws InvalidUsernameOrPasswordException, InvalidUserEmailException {
        user.setEmail(null);
        UserValidator.validateUser(user);
    }

    @Test(expected = InvalidUserEmailException.class)
    public void validateUserInvalidEmail2() throws InvalidUsernameOrPasswordException, InvalidUserEmailException {
        user.setEmail("invalidmail.com");
        UserValidator.validateUser(user);
    }

    @Test(expected = InvalidUserEmailException.class)
    public void validateUserInvalidEmail3() throws InvalidUsernameOrPasswordException, InvalidUserEmailException {
        user.setEmail("inva[lid@mail.com");
        UserValidator.validateUser(user);
    }

    @Test(expected = InvalidUsernameOrPasswordException.class)
    public void validateUserNullUsername() throws InvalidUsernameOrPasswordException, InvalidUserEmailException {
        user.setUsername(null);
        UserValidator.validateUser(user);
    }

    @Test(expected = InvalidUsernameOrPasswordException.class)
    public void validateUserInvalidUsername1() throws InvalidUsernameOrPasswordException, InvalidUserEmailException {
        user.setUsername("0Username");
        UserValidator.validateUser(user);
    }

    @Test(expected = InvalidUsernameOrPasswordException.class)
    public void validateUserInvalidUsername2() throws InvalidUsernameOrPasswordException, InvalidUserEmailException {
        user.setUsername("Usernameusernameusrenameusername");
        UserValidator.validateUser(user);
    }

    @Test(expected = InvalidUsernameOrPasswordException.class)
    public void validateUserInvalidUsername3() throws InvalidUsernameOrPasswordException, InvalidUserEmailException {
        user.setUsername("U");
        UserValidator.validateUser(user);
    }

    @Test(expected = InvalidUsernameOrPasswordException.class)
    public void validateUserInvalidUsername4() throws InvalidUsernameOrPasswordException, InvalidUserEmailException {
        user.setUsername("Userнame");
        UserValidator.validateUser(user);
    }

    @Test(expected = InvalidUsernameOrPasswordException.class)
    public void validateUserNullPassword() throws InvalidUsernameOrPasswordException, InvalidUserEmailException {
        user.setPassword(null);
        UserValidator.validateUser(user);
    }

    @Test(expected = InvalidUsernameOrPasswordException.class)
    public void validateUserInvalidPassword1() throws InvalidUsernameOrPasswordException, InvalidUserEmailException {
        user.setPassword("password123");
        UserValidator.validateUser(user);
    }

    @Test(expected = InvalidUsernameOrPasswordException.class)
    public void validateUserInvalidPassword2() throws InvalidUsernameOrPasswordException, InvalidUserEmailException {
        user.setPassword("passworD");
        UserValidator.validateUser(user);
    }

    @Test(expected = InvalidUsernameOrPasswordException.class)
    public void validateUserInvalidPassword3() throws InvalidUsernameOrPasswordException, InvalidUserEmailException {
        user.setPassword("pasSw1");
        UserValidator.validateUser(user);
    }

    @Test
    public void validatePasswordRepetitionPositive() throws PasswordRepetitionException {
        UserValidator.validatePasswordRepetition("Password123", "Password123");
    }

    @Test(expected = PasswordRepetitionException.class)
    public void validatePasswordRepetitionNullPassword() throws PasswordRepetitionException {
        UserValidator.validatePasswordRepetition(null, "Password123");
    }

    @Test(expected = PasswordRepetitionException.class)
    public void validatePasswordRepetitionNoMatch() throws PasswordRepetitionException {
        UserValidator.validatePasswordRepetition("Password124", "Password123");
    }

    @Test
    public void validateUserIsActivePositive() throws UserStatusIsNullOrBlockedException {
        UserValidator.validateUserIsActive(user);
    }

    @Test(expected = UserStatusIsNullOrBlockedException.class)
    public void validateUserIsActiveNullStatus() throws UserStatusIsNullOrBlockedException {
        user.setStatus(null);
        UserValidator.validateUserIsActive(user);
    }

    @Test(expected = UserStatusIsNullOrBlockedException.class)
    public void validateUserIsActiveStatusBlocked() throws UserStatusIsNullOrBlockedException {
        user.setStatus(UserStatus.BLOCKED);
        UserValidator.validateUserIsActive(user);
    }
}