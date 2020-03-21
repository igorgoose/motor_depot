package by.schepov.motordepot.builder.impl;

import by.schepov.motordepot.builder.AbstractBuilder;
import by.schepov.motordepot.entity.Route;

import java.sql.Date;

public class RouteBuilder extends AbstractBuilder<Route> {


    @Override
    public void reset() {
        object = new Route();
    }

    public RouteBuilder withId(int id){
        object.setId(id);
        return this;
    }

    public RouteBuilder withDepartureLocation(String departureLocation){
        object.setDepartureLocation(departureLocation);
        return this;
    }

    public RouteBuilder withDepartureTime(Date departureTime){
        object.setDepartureTime(departureTime);
        return this;
    }

    public RouteBuilder withArrivalLocation(String arrivalLocation){
        object.setArrivalLocation(arrivalLocation);
        return this;
    }

    public RouteBuilder withArrivalTime(Date arrivalTime){
        object.setArrivalTime(arrivalTime);
        return this;
    }
}
