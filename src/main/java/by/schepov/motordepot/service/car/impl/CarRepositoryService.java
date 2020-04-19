package by.schepov.motordepot.service.car.impl;

import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.exception.CarServiceException;
import by.schepov.motordepot.exception.repository.RepositoryException;
import by.schepov.motordepot.repository.impl.car.CarRepository;
import by.schepov.motordepot.service.RepositoryService;
import by.schepov.motordepot.service.car.CarService;
import by.schepov.motordepot.specification.impl.car.FindCarByIdSpecification;
import by.schepov.motordepot.specification.impl.car.FindCarsByDriverIdSpecification;
import by.schepov.motordepot.specification.impl.car.FindFreeCarsSpecification;
import by.schepov.motordepot.specification.impl.car.GetAllCarsSpecification;

import java.util.Set;

public class CarRepositoryService extends RepositoryService<Car> implements CarService {

    private CarRepositoryService() {
        super(CarRepository.INSTANCE);
    }


    public static class InstanceHolder{
        public static final CarRepositoryService INSTANCE = new CarRepositoryService();
    }

    public static CarRepositoryService getInstance(){
        return CarRepositoryService.InstanceHolder.INSTANCE;
    }

    @Override
    public Set<Car> getAllCars() throws CarServiceException {
        try {
            return repository.execute(new GetAllCarsSpecification());
        } catch (RepositoryException e) {
            throw new CarServiceException(e);
        }
    }

    @Override
    public Set<Car> findFreeCars(int loadCapacityRequired, int passengerCapacityRequired) throws CarServiceException {
        try {
            return repository.execute(new FindFreeCarsSpecification(loadCapacityRequired, passengerCapacityRequired));
        } catch (RepositoryException e) {
            throw new CarServiceException(e);
        }
    }

    @Override
    public Set<Car> findCarById(int id) throws CarServiceException {
        try {
            return repository.execute(new FindCarByIdSpecification(id));
        } catch (RepositoryException e) {
            throw new CarServiceException(e);
        }
    }

    @Override
    public Set<Car> getCarsByDriverId(int id) throws CarServiceException {
        try {
            return repository.execute(new FindCarsByDriverIdSpecification(id));
        } catch (RepositoryException e) {
            throw new CarServiceException(e);
        }
    }

}
