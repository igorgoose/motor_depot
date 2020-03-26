package by.schepov.motordepot.entity;

public enum CarModel {
    POLO(CarBrand.VOLKSWAGEN);

    private CarBrand carBrand;

    CarModel(CarBrand carBrand){
        this.carBrand = carBrand;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }
}
