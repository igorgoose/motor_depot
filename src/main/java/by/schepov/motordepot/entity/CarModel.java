package by.schepov.motordepot.entity;

import by.schepov.motordepot.exception.InvalidParameterException;

public enum CarModel {

    POLO("Polo"),
    JETTA("Jetta"),
    GOLF("Golf"),
    CADDY("Caddy"),
    FOCUS("Focus"),
    MONDEO("Mondeo"),
    RAPID("Rapid"),
    OCTAVIA("Octavia"),
    CIVIC("Civic"),
    PRIUS("Prius");

    private String name;

    CarModel(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CarModel getCarModelByName(String name){
        for (CarModel item: CarModel.values()){
            if(item.name.equals(name)){
                return item;
            }
        }
        throw new InvalidParameterException("Parameter passed: " + name);
    }

}
