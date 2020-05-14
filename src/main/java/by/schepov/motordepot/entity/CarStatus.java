package by.schepov.motordepot.entity;

public enum CarStatus {
    READY(1), BUSY(2), BROKEN(3);

    private final int id;

    CarStatus(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
