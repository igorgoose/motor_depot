package by.schepov.motordepot.builder;

/**
 * The interface providing methods of a classic builder.
 * The classes implementing this interface provide
 * a convenient way to construct objects with many fields and
 * help to avoid using constructors with a large number of parameters.
 *
 *
 * @param <T> the type of the element to be build by the builder
 *
 * @author Igor Schepov
 *
 *
 * @see  AbstractBuilder
 * @see  by.schepov.motordepot.builder.impl.car.CarBuilder
 * @see  by.schepov.motordepot.builder.impl.car.ResultSetCarBuilder
 * @see  by.schepov.motordepot.builder.impl.carname.CarNameBuilder
 * @see  by.schepov.motordepot.builder.impl.carname.ResultSetCarNameBuilder
 * @see  by.schepov.motordepot.builder.impl.order.OrderBuilder
 * @see  by.schepov.motordepot.builder.impl.order.ResultSetOrderBuilder
 * @see  by.schepov.motordepot.builder.impl.request.RequestBuilder
 * @see  by.schepov.motordepot.builder.impl.request.ResultSetRequestBuilder
 * @see  by.schepov.motordepot.builder.impl.user.UserBuilder
 * @see  by.schepov.motordepot.builder.impl.user.ResultSetUserBuilder
 *
 *
 */
public interface Builder<T> {

    /**
     * Refreshes the object inside the builder. Should be called before
     * constructing an object using other methods of {@link Builder}
     * implementations in order to avoid getting an object with
     * unwanted field values.
     *
     */
    void reset();


    /**
     * The method returns the object constructed using other methods of
     * {@link Builder} implementation called after the latest {@link #reset()} call.
     * This method does not refresh the object inside the builder.
     *
     *
     * @return the object constructed after the latest {@link #reset()} call
     */
    T build();
}
