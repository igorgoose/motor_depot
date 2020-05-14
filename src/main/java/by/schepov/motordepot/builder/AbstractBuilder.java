package by.schepov.motordepot.builder;


/**
 * Abstract class implementing {@link Builder} interface. Offers
 * a default constructor {@link #AbstractBuilder()} refreshing the object inside
 * the builder and the simplest implementation of {@link #build()} method.
 * Contains a protected field {@link #object} representing the object being
 * constructed.
 *
 *
 * @param <T> the type of the element to be build by the builder
 *
 * @author Igor Schepov
 *
 */
public abstract class AbstractBuilder<T> implements Builder<T> {

    /**
     * The object being constructed.
     */
    protected T object;

    /**
     * Implementation of the default constructor refreshing the {@link #object}.
     */
    public AbstractBuilder(){
        reset();
    }

    /**
     * @return he object constructed after the latest {@link #reset()} call
     */
    @Override
    public T build() {
        return object;
    }

}
