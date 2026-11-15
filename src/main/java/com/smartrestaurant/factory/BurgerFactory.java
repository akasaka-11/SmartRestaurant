package com.smartrestaurant.factory;

import com.smartrestaurant.factory.product.BasicMeal;
import com.smartrestaurant.factory.product.Meal;
import java.math.BigDecimal;

public final class BurgerFactory extends MealFactory {
    private static final BigDecimal PRICE = new BigDecimal("1800.00");

    @Override
    protected Meal prepareMeal() {
        return new BasicMeal("Burger", PRICE);
    }
}