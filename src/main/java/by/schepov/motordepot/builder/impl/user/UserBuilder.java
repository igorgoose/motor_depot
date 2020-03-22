package by.schepov.motordepot.builder.impl.user;

import by.schepov.motordepot.builder.AbstractBuilder;
import by.schepov.motordepot.entity.User;

public class UserBuilder extends AbstractBuilder<User> {

    @Override
    public void reset() {
        object = new User();
    }

    public UserBuilder withId(int id){
        object.setId(id);
        return this;
    }

    public UserBuilder withBlocked(boolean blocked){
        object.setBlocked(blocked);
        return this;
    }

    public UserBuilder withEmail(String email){
        object.setEmail(email);
        return this;
    }


    public UserBuilder withLogin(String login){
        object.setLogin(login);
        return this;
    }


    public UserBuilder withPassword(String password){
        object.setPassword(password);
        return this;
    }

}
