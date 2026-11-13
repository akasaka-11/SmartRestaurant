package com.smartrestaurant.factory;

import com.smartrestaurant.model.*;
import java.math.BigDecimal;

public final class PizzaFactory extends MealFactory {
    @Override protected Meal prepareMeal() { return new BasicMeal("Pizza", new BigDecimal("2500.00")); }
}
