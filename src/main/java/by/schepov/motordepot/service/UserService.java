package by.schepov.motordepot.service;

import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.service.UserServiceException;
import by.schepov.motordepot.repository.impl.user.UserRepository;
import by.schepov.motordepot.specification.impl.user.FindUserByUsernameSpecification;

import java.util.Set;

public enum UserService {
    INSTANCE;

    private final UserRepository userRepository = UserRepository.INSTANCE;

    public void insertUser(User user) throws UserServiceException {
        try {
            Set<User> similarLoginUsers = userRepository.execute(new FindUserByUsernameSpecification(user.getUsername()));
            if(similarLoginUsers.size() > 0){
                throw new UserServiceException("The username is already used");
            }
            userRepository.insert(user);
        } catch (RepositoryException e) {
            //todo log
            throw new UserServiceException(e);
        }
    }
}
