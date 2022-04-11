package org.example;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class RestaurantCoupon implements CouponConstraint {

    @Getter
    @Setter
    @NonNull
    private String restaurantName;

    @Override
    public boolean isFulfilled(Order order) {
        return false;
    }
}
