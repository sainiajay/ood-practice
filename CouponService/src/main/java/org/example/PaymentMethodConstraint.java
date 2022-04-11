package org.example;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class PaymentMethodConstraint implements CouponConstraint {

    @Getter
    @Setter
    @NonNull
    private String paymentMethod;

    @Override
    public boolean isFulfilled(Order order) {
        return false;
    }
}
