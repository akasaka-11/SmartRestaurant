package com.smartrestaurant.model;

import java.math.BigDecimal;
import java.util.Objects;

public record BasicMeal(String description, BigDecimal price) implements Meal {
    public BasicMeal {
        Objects.requireNonNull(description);
        Objects.requireNonNull(price);
        if (description.isBlank() || price.signum() < 0) throw new IllegalArgumentException("Invalid meal");
    }
}
