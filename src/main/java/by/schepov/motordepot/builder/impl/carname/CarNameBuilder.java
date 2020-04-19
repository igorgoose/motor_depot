package by.schepov.motordepot.builder.impl.carname;

import by.schepov.motordepot.builder.AbstractBuilder;
import by.schepov.motordepot.entity.CarBrand;
import by.schepov.motordepot.entity.CarModel;
import by.schepov.motordepot.entity.CarName;

public class CarNameBuilder extends AbstractBuilder<CarName> {

    public CarNameBuilder(){

    }

    @Override
    public void reset() {
        object = new CarName();
    }

    public CarNameBuilder withId(int id){
        object.setId(id);
        return this;
    }

    public CarNameBuilder withCarModel(CarModel carModel){
        object.setCarModel(carModel);
        return this;
    }

    public CarNameBuilder withCarBrand(CarBrand carBrand){
        object.setCarBrand(carBrand);
        return this;
    }

}
