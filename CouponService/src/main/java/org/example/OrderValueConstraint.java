package org.example;

public class OrderValueConstraint implements CouponConstraint {
    private final Integer minOrderValue;

    public OrderValueConstraint(Integer minOrderValue) {
        this.minOrderValue = minOrderValue;
    }

    @Override
    public boolean isFulfilled(Order order) {
        return order.getOrderValue() > this.minOrderValue;
    }
}
