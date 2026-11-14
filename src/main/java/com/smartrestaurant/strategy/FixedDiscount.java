package com.smartrestaurant.strategy;

import java.math.BigDecimal;
import java.util.Objects;

public final class FixedDiscount implements DiscountStrategy {
    private final BigDecimal amount;
    public FixedDiscount(BigDecimal amount) {
        this.amount = Objects.requireNonNull(amount);
        if (amount.signum() < 0) throw new IllegalArgumentException("Negative discount");
    }
    @Override public BigDecimal apply(BigDecimal subtotal) { return subtotal.subtract(amount).max(BigDecimal.ZERO); }
}
