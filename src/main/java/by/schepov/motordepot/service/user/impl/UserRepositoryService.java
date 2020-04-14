package by.schepov.motordepot.service.user.impl;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.service.UserServiceException;
import by.schepov.motordepot.repository.impl.user.UserRepository;
import by.schepov.motordepot.service.RepositoryService;
import by.schepov.motordepot.service.user.UserService;
import by.schepov.motordepot.specification.impl.user.FindUserByIdSpecification;
import by.schepov.motordepot.specification.impl.user.FindUserByUsernameSpecification;
import by.schepov.motordepot.specification.impl.user.GetAllUsersSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
            Set<User> similarLoginUsers = repository.execute(new FindUserByUsernameSpecification(user.getUsername()));
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
            Set<User> foundUsers = repository.execute(new FindUserByUsernameSpecification(user.getUsername()));
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
    public Set<User> getAllUsers() throws UserServiceException {
        try {
            return repository.execute(new GetAllUsersSpecification());
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            throw new UserServiceException(e);
        }
    }

    @Override
    public Set<User> getUsersById(int id) throws UserServiceException {
        try {
            return repository.execute(new FindUserByIdSpecification(id));
        } catch (RepositoryException e) {
            throw new UserServiceException(e);
        }
    }
}
