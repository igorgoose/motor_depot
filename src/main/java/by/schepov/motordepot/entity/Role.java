package by.schepov.motordepot.entity;

import java.io.Serializable;

public enum Role implements Serializable {
    ADMIN(1), DRIVER(2), USER(3), GUEST(4);

    private final int id;

    Role(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
