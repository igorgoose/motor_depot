package by.schepov.motordepot.builder.impl.order;

import by.schepov.motordepot.builder.Builder;
import by.schepov.motordepot.entity.Order;

public class ResultSetOrderBuilder implements Builder<Order> {

    private final OrderBuilder orderBuilder = new OrderBuilder();

    @Override
    public void reset() {
        orderBuilder.reset();
    }

    @Override
    public Order build() {
        return orderBuilder.build();
    }


}
