package org.example;

import java.util.List;

public class Coupon {

    public final String id;
    private List<CouponConstraint> constraints;

    public Coupon(String id) {
        this.id = id;
    }

    public void addConstraints(CouponConstraint constraint) {
        this.constraints.add(constraint);
    }

    public boolean isApplicable(Order order) {
        for(CouponConstraint constraint: constraints) {
            if(!constraint.isFulfilled(order)) {
                return false;
            }
        }
        return true;
    }
}
