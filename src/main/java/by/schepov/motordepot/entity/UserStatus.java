package by.schepov.motordepot.entity;

public enum UserStatus {
    ACTIVE(1, "ACTIVE"), BUSY(2, "BUSY"), BLOCKED(3, "BLOCKED");

    private final int id;
    private final String name;

    UserStatus(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
