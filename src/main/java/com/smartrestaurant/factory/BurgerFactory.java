package com.smartrestaurant.factory;

import com.smartrestaurant.model.*;
import java.math.BigDecimal;

public final class BurgerFactory extends MealFactory {
    @Override protected Meal prepareMeal() { return new BasicMeal("Burger", new BigDecimal("1800.00")); }
}
