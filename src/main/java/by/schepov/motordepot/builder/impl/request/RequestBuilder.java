package by.schepov.motordepot.builder.impl.request;

import by.schepov.motordepot.builder.AbstractBuilder;
import by.schepov.motordepot.entity.Request;
import by.schepov.motordepot.entity.Route;
import by.schepov.motordepot.entity.User;

public class RequestBuilder extends AbstractBuilder<Request> {

    @Override
    public void reset() {
        object = new Request();
    }

    public RequestBuilder withId(int id){
        object.setId(id);
        return this;
    }

    public RequestBuilder withUser(User user){
        object.setUser(user);
        return this;
    }

    public RequestBuilder withRoute(Route route){
        object.setRoute(route);
        return this;
    }

    public RequestBuilder withLoad(int load){
        object.setLoad(load);
        return this;
    }

    public RequestBuilder withPassengerQuantity(int passengerQuantity){
        object.setPassengersQuantity(passengerQuantity);
        return this;
    }


}
