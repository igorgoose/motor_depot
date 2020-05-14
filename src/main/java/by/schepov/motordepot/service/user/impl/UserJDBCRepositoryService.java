package by.schepov.motordepot.service.user.impl;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.entity.UserStatus;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.service.user.UserServiceException;
import by.schepov.motordepot.exception.validator.user.InvalidUserEmailException;
import by.schepov.motordepot.exception.validator.user.InvalidUsernameOrPasswordException;
import by.schepov.motordepot.exception.validator.user.PasswordRepetitionException;
import by.schepov.motordepot.exception.validator.user.UserStatusIsNullOrBlockedException;
import by.schepov.motordepot.factory.repository.impl.JDBCRepositoryFactory;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.repository.Repository;
import by.schepov.motordepot.repository.impl.user.UserJDBCRepository;
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

public class UserJDBCRepositoryService extends RepositoryService<User> implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserJDBCRepositoryService.class);

    private final Repository<User> repository;

    private UserJDBCRepositoryService() {
        super(JDBCRepositoryFactory.getInstance());
        repository = repositoryFactory.createUserRepository();
    }

    public static class InstanceHolder{
        public static final UserJDBCRepositoryService INSTANCE = new UserJDBCRepositoryService();
    }

    public static UserJDBCRepositoryService getInstance(){
        return UserJDBCRepositoryService.InstanceHolder.INSTANCE;
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
                LOGGER.info(e);
                throw e;
            }
            repository.insert(user);
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            UserServiceException ex = new UserServiceException(e);
            ex.setMessageBundleKey(MessageKey.UNEXPECTED_ERROR);
            throw ex;
        } catch (PasswordRepetitionException e) {
            LOGGER.info(e);
            UserServiceException ex = new UserServiceException(e);
            ex.setMessageBundleKey(MessageKey.PASSWORD_REPEATED_INCORRECTLY);
            throw ex;
        } catch (InvalidUserEmailException e) {
            LOGGER.info(e);
            UserServiceException ex = new UserServiceException(e);
            ex.setMessageBundleKey(MessageKey.INCORRECT_EMAIL);
            throw ex;
        } catch (InvalidUsernameOrPasswordException e) {
            LOGGER.info(e);
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
                    UserValidator.validateUserIsActive(user);
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
            LOGGER.info(e);
            UserServiceException ex = new UserServiceException(e);
            ex.setMessageBundleKey(MessageKey.WRONG_USERNAME_OR_PASSWORD);
            throw ex;
        } catch (UserStatusIsNullOrBlockedException e) {
            LOGGER.info(e);
            UserServiceException ex = new UserServiceException(e);
            ex.setMessageBundleKey(MessageKey.YOU_ARE_BLOCKED);
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
