package by.schepov.motordepot.entity;

import java.sql.Date;
import java.util.Objects;

public class Route {
    private int id;
    private Date departureTime;
    private String departureLocation;
    private Date arrivalTime;
    private String arrivalLocation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id == route.id &&
                Objects.equals(departureTime, route.departureTime) &&
                Objects.equals(departureLocation, route.departureLocation) &&
                Objects.equals(arrivalTime, route.arrivalTime) &&
                Objects.equals(arrivalLocation, route.arrivalLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departureTime, departureLocation, arrivalTime, arrivalLocation);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", departureTime=" + departureTime +
                ", departureLocation='" + departureLocation + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", arrivalLocation='" + arrivalLocation + '\'' +
                '}';
    }

}
