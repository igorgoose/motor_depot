package by.schepov.motordepot.service.user;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.service.UserServiceException;

import java.util.Set;

public interface UserService {
    void insertUser(User user) throws UserServiceException;
    void authorizeUser(User user) throws UserServiceException;
    void updateIsBlockedById(int id, boolean isBlocked) throws UserServiceException;
    Set<User> getAllUsers() throws UserServiceException;
    Set<User> getUsersById(int id) throws UserServiceException;
}
