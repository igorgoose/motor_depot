package by.schepov.motordepot.builder.impl.car;

import by.schepov.motordepot.builder.AbstractBuilder;
import by.schepov.motordepot.entity.*;

public class CarBuilder extends AbstractBuilder<Car> {

    public CarBuilder(){

    }

    @Override
    public void reset() {
        object = new Car();
    }

    public CarBuilder withId(int id){
        object.setId(id);
        return this;
    }

    public CarBuilder withDriver(User driver){
        object.setDriver(driver);
        return this;
    }

    public CarBuilder withRegistrationNumber(String registrationNumber){
        object.setRegistrationNumber(registrationNumber);
        return this;
    }

    public CarBuilder withCarName(CarName carName){
        object.setCarName(carName);
        return this;
    }

    public CarBuilder withLoadCapacity(int loadCapacity){
        object.setLoadCapacity(loadCapacity);
        return this;
    }

    public CarBuilder withPassengerCapacity(int passengerCapacity){
        object.setPassengerCapacity(passengerCapacity);
        return this;
    }

    public CarBuilder withCarStatus(CarStatus carStatus){
        object.setCarStatus(carStatus);
        return this;
    }
}
