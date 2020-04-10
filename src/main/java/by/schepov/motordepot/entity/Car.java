package by.schepov.motordepot.entity;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {
    private int id;
    private User driver;
    private String registrationNumber;
    private CarName carName;
    private int loadCapacity;
    private int passengerCapacity;
    private CarStatus carStatus;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public CarName getCarName() {
        return carName;
    }

    public void setCarName(CarName carName) {
        this.carName = carName;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                loadCapacity == car.loadCapacity &&
                passengerCapacity == car.passengerCapacity &&
                Objects.equals(driver, car.driver) &&
                Objects.equals(registrationNumber, car.registrationNumber) &&
                carName == car.carName &&
                carStatus == car.carStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, driver, registrationNumber, carName, loadCapacity, passengerCapacity, carStatus);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", driver=" + driver +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", carName=" + carName +
                ", loadCapacity=" + loadCapacity +
                ", passengerCapacity=" + passengerCapacity +
                ", carStatus=" + carStatus +
                '}';
    }
}
