package com.smartrestaurant.decorator;

import com.smartrestaurant.factory.product.Meal;
import java.math.BigDecimal;
import java.util.Objects;

public abstract class MealDecorator implements Meal {
    protected final Meal wrapped;

    protected MealDecorator(Meal wrapped) {
        this.wrapped = Objects.requireNonNull(wrapped);
    }

    protected abstract String label();

    protected abstract BigDecimal surcharge();

    @Override
    public final String description() {
        return wrapped.description() + " + " + label();
    }

    @Override
    public final BigDecimal price() {
        return wrapped.price().add(surcharge());
    }
}