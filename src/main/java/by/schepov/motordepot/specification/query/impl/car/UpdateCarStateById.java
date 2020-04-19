package by.schepov.motordepot.specification.query.impl.car;

import by.schepov.motordepot.entity.Car;
import by.schepov.motordepot.exception.specification.SpecificationException;
import by.schepov.motordepot.specification.query.QuerySpecification;

import java.util.Set;

public class UpdateCarStateById implements QuerySpecification<Car> {

    @Override
    public Set<Car> execute() throws SpecificationException {
        return null;
    }
}
