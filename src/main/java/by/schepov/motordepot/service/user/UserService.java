package by.schepov.motordepot.service.user;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.entity.UserStatus;
import by.schepov.motordepot.exception.service.user.UserServiceException;
import by.schepov.motordepot.exception.validator.InvalidUsernameOrPasswordException;

import java.util.Set;

public interface UserService {
    void signUpUser(User user, String repeatedPassword) throws UserServiceException;
    void authorizeUser(User user) throws UserServiceException, InvalidUsernameOrPasswordException;
    void updateStatusById(int id, UserStatus status) throws UserServiceException;
    Set<User> getAllUsers() throws UserServiceException;
    User getUserById(int id) throws UserServiceException;
}
