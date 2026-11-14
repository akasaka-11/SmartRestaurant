package com.smartrestaurant.strategy;

import java.math.*;

public final class PercentageDiscount implements DiscountStrategy {
    private final int percent;
    public PercentageDiscount(int percent) {
        if (percent < 0 || percent > 100) throw new IllegalArgumentException("Discount must be 0..100");
        this.percent = percent;
    }
    @Override public BigDecimal apply(BigDecimal subtotal) {
        return subtotal.multiply(BigDecimal.valueOf(100 - percent)).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }
}
