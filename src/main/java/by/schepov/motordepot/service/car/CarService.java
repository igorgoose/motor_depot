package by.schepov.motordepot.service.car;

import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.CarStatus;
import by.schepov.motordepot.exception.CarServiceException;

import java.util.Set;

public interface CarService {
    Set<Car> getAllCars() throws CarServiceException;
    Set<Car> findFreeCars(int loadCapacityRequired, int passengerCapacityRequired) throws CarServiceException;
    Set<Car> findCarById(int id) throws CarServiceException;
    Set<Car> getCarsByDriverId(int id) throws CarServiceException;
    void updateCarStatus(int id, CarStatus carStatus) throws CarServiceException;
}
