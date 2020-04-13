package by.schepov.motordepot.entity;

import java.util.Objects;

public class CarName {

    private int id;
    private CarModel carModel;
    private CarBrand carBrand;

    public CarName(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarName carName = (CarName) o;
        return id == carName.id &&
                carModel == carName.carModel &&
                carBrand == carName.carBrand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carModel, carBrand);
    }

    @Override
    public String toString() {
        return carBrand.getName() + " " + carModel.getName();
    }

}
