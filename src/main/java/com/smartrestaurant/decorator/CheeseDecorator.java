package com.smartrestaurant.decorator;

import com.smartrestaurant.factory.product.Meal;
import java.math.BigDecimal;

public final class CheeseDecorator extends MealDecorator {
    private static final BigDecimal SURCHARGE = new BigDecimal("300.00");

    public CheeseDecorator(Meal wrapped) {
        super(wrapped);
    }

    @Override
    protected String label() {
        return "cheese";
    }

    @Override
    protected BigDecimal surcharge() {
        return SURCHARGE;
    }
}