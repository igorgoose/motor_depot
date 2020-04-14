package by.schepov.motordepot.entity;

import java.util.Objects;

public class Request {
    private int id;
    private User user;
    private String departureLocation;
    private String arrivalLocation;
    private int passengersQuantity;
    private int load;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public int getPassengersQuantity() {
        return passengersQuantity;
    }

    public void setPassengersQuantity(int passengersQuantity) {
        this.passengersQuantity = passengersQuantity;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id &&
                passengersQuantity == request.passengersQuantity &&
                load == request.load &&
                Objects.equals(user, request.user) &&
                Objects.equals(departureLocation, request.departureLocation) &&
                Objects.equals(arrivalLocation, request.arrivalLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, departureLocation, arrivalLocation, passengersQuantity, load);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", user=" + user +
                ", departureLocation='" + departureLocation + '\'' +
                ", arrivalLocation='" + arrivalLocation + '\'' +
                ", passengersQuantity=" + passengersQuantity +
                ", load=" + load +
                '}';
    }
}
