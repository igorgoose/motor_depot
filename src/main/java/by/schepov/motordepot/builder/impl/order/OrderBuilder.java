package by.schepov.motordepot.builder.impl.order;

import by.schepov.motordepot.builder.AbstractBuilder;
import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.Order;
import by.schepov.motordepot.entity.User;

public class OrderBuilder extends AbstractBuilder<Order> {

    @Override
    public void reset() {
        object = new Order();
    }

    public OrderBuilder withId(int id){
        object.setId(id);
        return this;
    }

    public OrderBuilder withUser(User user){
        object.setUser(user);
        return this;
    }

    public OrderBuilder withDriver(User driver){
        object.setDriver(driver);
        return this;
    }

    public OrderBuilder withCar(Car car) {
        object.setCar(car);
        return this;
    }

    public OrderBuilder withDepartureLocation(String departureLocation){
        object.setDepartureLocation(departureLocation);
        return this;
    }

    public OrderBuilder withArrivalLocation(String arrivalLocation){
        object.setArrivalLocation(arrivalLocation);
        return this;
    }

}
