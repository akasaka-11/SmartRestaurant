package com.smartrestaurant;

import com.smartrestaurant.builder.Order;
import com.smartrestaurant.decorator.*;
import com.smartrestaurant.factory.*;
import com.smartrestaurant.facade.RestaurantFacade;
import com.smartrestaurant.observer.OrderObserver;
import com.smartrestaurant.strategy.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    @Test void factoryAndDecorator() {
        var meal = new SauceDecorator(new CheeseDecorator(new PizzaFactory().create()));
        assertEquals(new BigDecimal("2950.00"), meal.price());
        assertEquals("Pizza + cheese + sauce", meal.description());
        assertEquals(new BigDecimal("1800.00"), new BurgerFactory().create().price());
    }
    @Test void builderAndStrategy() {
        var order = Order.builder("a").customer("Dana").addMeal(new PizzaFactory().create()).build();
        assertEquals(new BigDecimal("2250.00"), new PercentageDiscount(10).apply(order.subtotal()));
        assertEquals(new BigDecimal("2000.00"), new FixedDiscount(new BigDecimal("500.00")).apply(order.subtotal()));
        assertThrows(UnsupportedOperationException.class, () -> order.meals().clear());
        assertThrows(IllegalStateException.class, () -> Order.builder("x").customer("Dana").build());
    }
    @Test void facadeAndObserver() {
        var events = new ArrayList<String>();
        OrderObserver observer = (id, status) -> events.add(id + ":" + status);
        var restaurant = new RestaurantFacade();
        restaurant.addObserver(observer);
        var order = Order.builder("b").customer("Dana").addMeal(new BurgerFactory().create()).build();
        assertEquals(new BigDecimal("1800.00"), restaurant.placeOrder(order, new NoDiscount()));
        restaurant.changeStatus("b", "PREPARING");
        restaurant.changeStatus("b", "READY");
        restaurant.changeStatus("b", "COMPLETED");
        assertEquals(List.of("b:PLACED", "b:PREPARING", "b:READY", "b:COMPLETED"), events);
        assertThrows(IllegalArgumentException.class, () -> restaurant.changeStatus("b", "PLACED"));
        assertThrows(IllegalArgumentException.class, () -> restaurant.placeOrder(order, new NoDiscount()));
    }
}
