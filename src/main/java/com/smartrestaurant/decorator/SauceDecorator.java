package com.smartrestaurant.decorator;

import com.smartrestaurant.model.Meal;
import java.math.BigDecimal;

public final class SauceDecorator extends MealDecorator {
    public SauceDecorator(Meal wrapped) { super(wrapped); }
    @Override public String description() { return wrapped.description() + " + sauce"; }
    @Override public BigDecimal price() { return wrapped.price().add(new BigDecimal("150.00")); }
}
