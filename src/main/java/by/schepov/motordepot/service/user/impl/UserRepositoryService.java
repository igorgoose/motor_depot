package by.schepov.motordepot.service.user.impl;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.entity.UserStatus;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.service.user.UserServiceException;
import by.schepov.motordepot.exception.validator.InvalidUserEmailException;
import by.schepov.motordepot.exception.validator.InvalidUsernameOrPasswordException;
import by.schepov.motordepot.exception.validator.PasswordRepetitionException;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.repository.impl.user.UserRepository;
import by.schepov.motordepot.service.RepositoryService;
import by.schepov.motordepot.service.user.UserService;
import by.schepov.motordepot.specification.query.impl.user.FindUserByIdQuerySpecification;
import by.schepov.motordepot.specification.query.impl.user.FindUserByUsernameQuerySpecification;
import by.schepov.motordepot.specification.query.impl.user.GetAllUsersQuerySpecification;
import by.schepov.motordepot.specification.update.user.UpdateUserBlockedSpecification;
import by.schepov.motordepot.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Set;

public class UserRepositoryService extends RepositoryService<User> implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserRepositoryService.class);

    private UserRepositoryService() {
        super(UserRepository.INSTANCE);
    }

    public static class InstanceHolder{
        public static final UserRepositoryService INSTANCE = new UserRepositoryService();
    }

    public static UserRepositoryService getInstance(){
        return UserRepositoryService.InstanceHolder.INSTANCE;
    }

    @Override
    public void signUpUser(User user, String repeatedPassword) throws UserServiceException {
        try {
            UserValidator.validateUser(user);
            UserValidator.validatePasswordRepetition(user.getPassword(), repeatedPassword);
            Set<User> similarLoginUsers = repository.executeQuery(new FindUserByUsernameQuerySpecification(user.getUsername()));
            if(similarLoginUsers.size() > 0){
                UserServiceException e = new UserServiceException("The username is already taken");
                e.setMessageBundleKey(MessageKey.USERNAME_TAKEN);
                LOGGER.warn(e);
                throw e;
            }
            repository.insert(user);
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            UserServiceException ex = new UserServiceException(e);
            ex.setMessageBundleKey(MessageKey.UNEXPECTED_ERROR);
            throw ex;
        } catch (PasswordRepetitionException e) {
            LOGGER.warn(e);
            UserServiceException ex = new UserServiceException(e);
            ex.setMessageBundleKey(MessageKey.PASSWORD_REPEATED_INCORRECTLY);
            throw ex;
        } catch (InvalidUserEmailException e) {
            LOGGER.warn(e);
            UserServiceException ex = new UserServiceException(e);
            ex.setMessageBundleKey(MessageKey.INCORRECT_EMAIL);
            throw ex;
        } catch (InvalidUsernameOrPasswordException e) {
            LOGGER.warn(e);
            UserServiceException ex = new UserServiceException(e);
            ex.setMessageBundleKey(MessageKey.WRONG_USERNAME_OR_PASSWORD);
            throw ex;
        }
    }

    @Override
    public void authorizeUser(User user) throws UserServiceException {
        try {
            UserValidator.validateUsernameAndPassword(user);
            Set<User> foundUsers = repository.executeQuery(new FindUserByUsernameQuerySpecification(user.getUsername()));
            for (User foundUser : foundUsers) {
                if(foundUser.getPassword().equals(user.getPassword())){
                    user.setId(foundUser.getId());
                    user.setRole(foundUser.getRole());
                    user.setEmail(foundUser.getEmail());
                    user.setStatus(foundUser.getStatus());
                    return;
                }
            }
            UserServiceException ex = new UserServiceException("Invalid user data " + user);
            ex.setMessageBundleKey(MessageKey.WRONG_USERNAME_OR_PASSWORD);
            throw ex;
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            UserServiceException ex = new UserServiceException(e);
            ex.setMessageBundleKey(MessageKey.UNEXPECTED_ERROR);
            throw ex;
        } catch (InvalidUsernameOrPasswordException e) {
            LOGGER.warn(e);
            UserServiceException ex = new UserServiceException(e);
            ex.setMessageBundleKey(MessageKey.WRONG_USERNAME_OR_PASSWORD);
            throw ex;
        }
    }

    @Override
    public void updateStatusById(int id, UserStatus status) throws UserServiceException {
        try {
            repository.executeUpdate(new UpdateUserBlockedSpecification(id, status));
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new UserServiceException(e);
        }
    }

    @Override
    public Set<User> getAllUsers() throws UserServiceException {
        try {
            return repository.executeQuery(new GetAllUsersQuerySpecification());
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            UserServiceException ex = new UserServiceException(e);
            ex.setMessageBundleKey(MessageKey.UNEXPECTED_ERROR);
            throw ex;
        }
    }

    @Override
    public User getUserById(int id) throws UserServiceException {
        try {
            Set<User> users = repository.executeQuery(new FindUserByIdQuerySpecification(id));
            Iterator<User> iterator = users.iterator();
            return iterator.hasNext() ? iterator.next() : null;
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            UserServiceException ex = new UserServiceException(e);
            ex.setMessageBundleKey(MessageKey.UNEXPECTED_ERROR);
            throw ex;
        }
    }
}
