package by.schepov.motordepot.entity;

import by.schepov.motordepot.exception.InvalidParameterException;

public enum CarBrand {
    VOLKSWAGEN("Volkswagen");

    private String name;

    CarBrand(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CarBrand getCarBrandByName(String name){
        for (CarBrand item: CarBrand.values()){
            if(item.name.equals(name)){
                return item;
            }
        }
        throw new InvalidParameterException("Parameter passed: " + name);
    }

}
