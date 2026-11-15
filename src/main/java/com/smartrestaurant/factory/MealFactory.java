package com.smartrestaurant.factory;

import com.smartrestaurant.factory.product.Meal;
import java.util.Objects;

public abstract class MealFactory {
    public final Meal create() {
        return Objects.requireNonNull(prepareMeal(), "Factory returned no meal");
    }

    protected abstract Meal prepareMeal();
}