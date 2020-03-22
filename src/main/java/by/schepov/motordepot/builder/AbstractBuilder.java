package by.schepov.motordepot.builder;

public abstract class AbstractBuilder<T> implements Builder<T> {

    protected T object;

    @Override
    public T build() {
        return object;
    }

}
