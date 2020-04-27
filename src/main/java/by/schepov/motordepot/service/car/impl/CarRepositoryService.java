package by.schepov.motordepot.service.car.impl;

import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.CarStatus;
import by.schepov.motordepot.exception.CarServiceException;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.repository.impl.car.CarRepository;
import by.schepov.motordepot.service.RepositoryService;
import by.schepov.motordepot.service.car.CarService;
import by.schepov.motordepot.specification.query.impl.car.FindCarByIdQuerySpecification;
import by.schepov.motordepot.specification.query.impl.car.FindCarsByDriverIdQuerySpecification;
import by.schepov.motordepot.specification.query.impl.car.FindFreeCarsQuerySpecification;
import by.schepov.motordepot.specification.query.impl.car.GetAllCarsQuerySpecification;
import by.schepov.motordepot.specification.update.car.UpdateCarStateSpecification;

import java.util.Iterator;
import java.util.Set;

public class CarRepositoryService extends RepositoryService<Car> implements CarService {

    private CarRepositoryService() {
        super(CarRepository.INSTANCE);
    }


    public static class InstanceHolder {
        public static final CarRepositoryService INSTANCE = new CarRepositoryService();
    }

    public static CarRepositoryService getInstance() {
        return CarRepositoryService.InstanceHolder.INSTANCE;
    }

    @Override
    public Set<Car> getAllCars() throws CarServiceException {
        try {
            return repository.executeQuery(new GetAllCarsQuerySpecification());
        } catch (RepositoryException e) {
            throw new CarServiceException(e);
        }
    }

    @Override
    public Set<Car> findFreeCars(int loadCapacityRequired, int passengerCapacityRequired) throws CarServiceException {
        try {
            return repository.executeQuery(new FindFreeCarsQuerySpecification(loadCapacityRequired, passengerCapacityRequired));
        } catch (RepositoryException e) {
            throw new CarServiceException(e);
        }
    }

    @Override
    public Car findCarById(int id) throws CarServiceException {
        try {
            Set<Car> cars = repository.executeQuery(new FindCarByIdQuerySpecification(id));
            Iterator<Car> carIterator = cars.iterator();
            return carIterator.hasNext() ? carIterator.next() : null;
        } catch (RepositoryException e) {
            throw new CarServiceException(e);
        }
    }

    @Override
    public Set<Car> getCarsByDriverId(int id) throws CarServiceException {
        try {
            return repository.executeQuery(new FindCarsByDriverIdQuerySpecification(id));
        } catch (RepositoryException e) {
            throw new CarServiceException(e);
        }
    }

    @Override
    public void updateCarStatus(int id, CarStatus carStatus) throws CarServiceException {
        try {
            repository.executeUpdate(new UpdateCarStateSpecification(id, carStatus));
        } catch (RepositoryException e) {
            throw new CarServiceException(e);
        }
    }


}
