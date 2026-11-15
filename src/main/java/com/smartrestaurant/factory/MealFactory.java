package com.smartrestaurant.factory;

import com.smartrestaurant.factory.product.Meal;

public abstract class MealFactory {
    public final Meal create() { return prepareMeal(); }
    protected abstract Meal prepareMeal();
}
