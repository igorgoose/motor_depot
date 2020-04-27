package by.schepov.motordepot.service.user.impl;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.service.UserServiceException;
import by.schepov.motordepot.repository.impl.user.UserRepository;
import by.schepov.motordepot.service.RepositoryService;
import by.schepov.motordepot.service.user.UserService;
import by.schepov.motordepot.specification.query.impl.user.FindUserByIdQuerySpecification;
import by.schepov.motordepot.specification.query.impl.user.FindUserByUsernameQuerySpecification;
import by.schepov.motordepot.specification.query.impl.user.GetAllUsersQuerySpecification;
import by.schepov.motordepot.specification.update.user.UpdateUserBlockedSpecification;
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
    public void insertUser(User user) throws UserServiceException {
        try {
            Set<User> similarLoginUsers = repository.executeQuery(new FindUserByUsernameQuerySpecification(user.getUsername()));
            if(similarLoginUsers.size() > 0){
                throw new UserServiceException("The username is already used");
            }
            repository.insert(user);
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new UserServiceException(e);
        }
    }

    @Override
    public void authorizeUser(User user) throws UserServiceException {
        try {
            Set<User> foundUsers = repository.executeQuery(new FindUserByUsernameQuerySpecification(user.getUsername()));
            for (User foundUser : foundUsers) {
                if(foundUser.getPassword().equals(user.getPassword())){
                    user.setId(foundUser.getId());
                    user.setRole(foundUser.getRole());
                    user.setEmail(foundUser.getEmail());
                    return;
                }
            }
            throw new UserServiceException("Invalid user data");
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new UserServiceException(e);
        }
    }

    @Override
    public void updateIsBlockedById(int id, boolean isBlocked) throws UserServiceException {
        try {
            repository.executeUpdate(new UpdateUserBlockedSpecification(id, isBlocked));
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
            throw new UserServiceException(e);
        }
    }

    @Override
    public User getUserById(int id) throws UserServiceException {
        try {
            Set<User> users = repository.executeQuery(new FindUserByIdQuerySpecification(id));
            Iterator<User> iterator = users.iterator();
            return iterator.hasNext() ? iterator.next() : null;
        } catch (RepositoryException e) {
            throw new UserServiceException(e);
        }
    }
}
