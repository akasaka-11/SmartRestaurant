package com.smartrestaurant.factory;

import com.smartrestaurant.factory.product.BasicMeal;
import com.smartrestaurant.factory.product.Meal;
import java.math.BigDecimal;

public final class PizzaFactory extends MealFactory {
    private static final BigDecimal PRICE = new BigDecimal("2500.00");

    @Override
    protected Meal prepareMeal() {
        return new BasicMeal("Pizza", PRICE);
    }
}