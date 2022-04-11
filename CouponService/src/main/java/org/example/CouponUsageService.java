package org.example;

import java.time.LocalDateTime;

public class CouponUsageService {
    public LocalDateTime couponLastUsedBy(String couponId, String userId) {
        return LocalDateTime.now().minusHours(10);
    }
}
