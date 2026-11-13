package com.smartrestaurant.decorator;

import com.smartrestaurant.model.Meal;
import java.util.Objects;

public abstract class MealDecorator implements Meal {
    protected final Meal wrapped;
    protected MealDecorator(Meal wrapped) { this.wrapped = Objects.requireNonNull(wrapped); }
}
