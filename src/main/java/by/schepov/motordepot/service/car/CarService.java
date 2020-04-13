package by.schepov.motordepot.service.car;

import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.exception.CarServiceException;

import java.util.Set;

public interface CarService {
    Set<Car> getAllCars() throws CarServiceException;
}