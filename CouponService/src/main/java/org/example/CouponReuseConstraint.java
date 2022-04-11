package org.example;
import java.time.LocalDateTime;
import java.time.Period;

public class CouponReuseConstraint implements CouponConstraint {

    private final CouponUsageService couponUsageService;
    private final Period period;
    private final String couponId;

    public CouponReuseConstraint(CouponUsageService couponUsageService, String periodString, String couponId) {
        this.couponUsageService = couponUsageService;
        this.period = Period.parse(periodString);
        this.couponId = couponId;
    }

    @Override
    public boolean isFulfilled(Order order) {
        return this.couponUsageService.couponLastUsedBy(this.couponId, order.getUserId())
                .isBefore(LocalDateTime.now().minus(this.period));
    }
}
