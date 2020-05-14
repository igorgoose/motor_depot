package by.schepov.motordepot.service.car.impl;

import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.entity.CarStatus;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.exception.service.CarServiceException;
import by.schepov.motordepot.factory.repository.impl.JDBCRepositoryFactory;
import by.schepov.motordepot.parameter.MessageKey;
import by.schepov.motordepot.repository.Repository;
import by.schepov.motordepot.service.RepositoryService;
import by.schepov.motordepot.service.car.CarService;
import by.schepov.motordepot.specification.query.impl.car.FindCarByIdQuerySpecification;
import by.schepov.motordepot.specification.query.impl.car.FindCarsByDriverIdQuerySpecification;
import by.schepov.motordepot.specification.query.impl.car.FindFreeCarsQuerySpecification;
import by.schepov.motordepot.specification.query.impl.car.GetAllCarsQuerySpecification;
import by.schepov.motordepot.specification.update.car.UpdateCarStateSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Set;

public class CarJDBCRepositoryService extends RepositoryService<Car> implements CarService {


    private static final Logger LOGGER = LogManager.getLogger(CarJDBCRepositoryService.class);
    private final Repository<Car> repository;

    private CarJDBCRepositoryService() {
        super(JDBCRepositoryFactory.getInstance());
        repository = repositoryFactory.createCarRepository();
    }


    public static class InstanceHolder {
        public static final CarJDBCRepositoryService INSTANCE = new CarJDBCRepositoryService();
    }

    public static CarJDBCRepositoryService getInstance() {
        return CarJDBCRepositoryService.InstanceHolder.INSTANCE;
    }

    @Override
    public Set<Car> getAllCars() throws CarServiceException {
        try {
            return repository.executeQuery(new GetAllCarsQuerySpecification());
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            CarServiceException ex = new CarServiceException(e);
            ex.setMessageBundleKey(MessageKey.UNEXPECTED_ERROR);
            throw ex;
        }
    }

    @Override
    public Set<Car> findFreeCars(int loadCapacityRequired, int passengerCapacityRequired) throws CarServiceException {
        try {
            return repository.executeQuery(new FindFreeCarsQuerySpecification(loadCapacityRequired, passengerCapacityRequired));
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            CarServiceException ex = new CarServiceException(e);
            ex.setMessageBundleKey(MessageKey.UNEXPECTED_ERROR);
            throw ex;
        }
    }

    @Override
    public Car findCarById(int id) throws CarServiceException {
        try {
            Set<Car> cars = repository.executeQuery(new FindCarByIdQuerySpecification(id));
            Iterator<Car> carIterator = cars.iterator();
            return carIterator.hasNext() ? carIterator.next() : null;
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            CarServiceException ex = new CarServiceException(e);
            ex.setMessageBundleKey(MessageKey.UNEXPECTED_ERROR);
            throw ex;
        }
    }

    @Override
    public Set<Car> getCarsByDriverId(int id) throws CarServiceException {
        try {
            return repository.executeQuery(new FindCarsByDriverIdQuerySpecification(id));
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            CarServiceException ex = new CarServiceException(e);
            ex.setMessageBundleKey(MessageKey.UNEXPECTED_ERROR);
            throw ex;
        }
    }

    @Override
    public void updateCarStatus(int id, CarStatus carStatus) throws CarServiceException {
        try {
            repository.executeUpdate(new UpdateCarStateSpecification(id, carStatus));
        } catch (RepositoryException e) {
            LOGGER.warn(e);
            CarServiceException ex = new CarServiceException(e);
            ex.setMessageBundleKey(MessageKey.UNEXPECTED_ERROR);
            throw ex;
        }
    }


}
