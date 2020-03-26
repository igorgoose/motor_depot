package by.schepov.motordepot.entity;

import java.util.Objects;

public class Order {
    private int id;
    private User user;
    private Route route;
    private Car car;
    private User driver;
    private boolean isComplete;

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

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
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
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                isComplete == order.isComplete &&
                Objects.equals(user, order.user) &&
                Objects.equals(route, order.route) &&
                Objects.equals(car, order.car) &&
                Objects.equals(driver, order.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, route, car, driver, isComplete);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", route=" + route +
                ", car=" + car +
                ", driver=" + driver +
                ", isComplete=" + isComplete +
                '}';
    }
}
