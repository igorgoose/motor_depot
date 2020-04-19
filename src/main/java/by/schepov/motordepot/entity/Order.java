package by.schepov.motordepot.entity;

import java.util.Objects;

public class Order {
    private int id;
    private User user;
    private String departureLocation;
    private String arrivalLocation;
    private Car car;
    private User driver;
    private boolean complete;

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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                complete == order.complete &&
                Objects.equals(user, order.user) &&
                Objects.equals(departureLocation, order.departureLocation) &&
                Objects.equals(arrivalLocation, order.arrivalLocation) &&
                Objects.equals(car, order.car) &&
                Objects.equals(driver, order.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, departureLocation, arrivalLocation, car, driver, complete);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", departureLocation='" + departureLocation + '\'' +
                ", arrivalLocation='" + arrivalLocation + '\'' +
                ", car=" + car +
                ", driver=" + driver +
                ", isComplete=" + complete +
                '}';
    }
}
