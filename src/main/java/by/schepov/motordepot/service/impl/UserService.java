package by.schepov.motordepot.service.impl;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.service.UserServiceException;
import by.schepov.motordepot.repository.impl.user.UserRepository;
import by.schepov.motordepot.service.Service;
import by.schepov.motordepot.specification.impl.user.FindUserByUsernameSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class UserService extends Service<User> {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    private UserService() {
        super(UserRepository.INSTANCE);
    }

    public static class InstanceHolder{
        public static final UserService INSTANCE = new UserService();
    }

    public static UserService getInstance(){
        return UserService.InstanceHolder.INSTANCE;
    }

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
}
