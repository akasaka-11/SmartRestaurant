package com.smartrestaurant.decorator;

import com.smartrestaurant.model.Meal;
import java.math.BigDecimal;

public final class CheeseDecorator extends MealDecorator {
    public CheeseDecorator(Meal wrapped) { super(wrapped); }
    @Override public String description() { return wrapped.description() + " + cheese"; }
    @Override public BigDecimal price() { return wrapped.price().add(new BigDecimal("300.00")); }
}
