package com.smartrestaurant.decorator;

import com.smartrestaurant.factory.product.Meal;
import java.math.BigDecimal;

public final class SauceDecorator extends MealDecorator {
    private static final BigDecimal SURCHARGE = new BigDecimal("150.00");

    public SauceDecorator(Meal wrapped) {
        super(wrapped);
    }

    @Override
    protected String label() {
        return "sauce";
    }

    @Override
    protected BigDecimal surcharge() {
        return SURCHARGE;
    }
}