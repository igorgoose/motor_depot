package by.schepov.motordepot.builder;

public interface Builder<T> {
    void reset();
    T build();
}
