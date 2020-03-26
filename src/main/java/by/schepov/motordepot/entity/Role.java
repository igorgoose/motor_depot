package by.schepov.motordepot.entity;

import java.io.Serializable;

public enum Role implements Serializable {
    ADMIN(1), USER(3), DRIVER(2), GUEST(4);

    private int id;

    Role(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
