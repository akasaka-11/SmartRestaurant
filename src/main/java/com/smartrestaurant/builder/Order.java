package com.smartrestaurant.builder;

import com.smartrestaurant.model.Meal;
import java.math.BigDecimal;
import java.util.*;

public final class Order {
    private final String id, customer, address;
    private final List<Meal> meals;
    private Order(Builder b) {
        id = b.id; customer = b.customer; address = b.address; meals = List.copyOf(b.meals);
    }
    public String id() { return id; }
    public String customer() { return customer; }
    public String address() { return address; }
    public List<Meal> meals() { return meals; }
    public BigDecimal subtotal() { return meals.stream().map(Meal::price).reduce(BigDecimal.ZERO, BigDecimal::add); }
    public static Builder builder(String id) { return new Builder(id); }
    public static final class Builder {
        private final String id;
        private String customer, address = "Pickup";
        private final List<Meal> meals = new ArrayList<>();
        private Builder(String id) { this.id = Objects.requireNonNull(id); }
        public Builder customer(String name) { customer = name; return this; }
        public Builder address(String address) { this.address = address; return this; }
        public Builder addMeal(Meal meal) { meals.add(Objects.requireNonNull(meal)); return this; }
        public Order build() {
            if (id.isBlank() || customer == null || customer.isBlank() || address == null || address.isBlank() || meals.isEmpty())
                throw new IllegalStateException("Order requires id, customer, address and at least one meal");
            return new Order(this);
        }
    }
}
