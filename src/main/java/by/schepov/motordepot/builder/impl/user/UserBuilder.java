package by.schepov.motordepot.builder.impl.user;

import by.schepov.motordepot.builder.AbstractBuilder;
import by.schepov.motordepot.entity.Role;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.entity.UserStatus;

public class UserBuilder extends AbstractBuilder<User> {

    public UserBuilder(){

    }

    @Override
    public void reset() {
        object = new User();
    }

    public UserBuilder withId(int id){
        object.setId(id);
        return this;
    }

    public UserBuilder withStatus(UserStatus status){
        object.setStatus(status);
        return this;
    }

    public UserBuilder withEmail(String email){
        object.setEmail(email);
        return this;
    }


    public UserBuilder withLogin(String login){
        object.setUsername(login);
        return this;
    }


    public UserBuilder withPassword(String password){
        object.setPassword(password);
        return this;
    }

    public UserBuilder withRole(Role role){
        object.setRole(role);
        return this;
    }
}
