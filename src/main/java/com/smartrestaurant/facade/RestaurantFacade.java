package com.smartrestaurant.facade;

import com.smartrestaurant.builder.Order;
import com.smartrestaurant.observer.*;
import com.smartrestaurant.strategy.DiscountStrategy;
import java.math.BigDecimal;
import java.util.*;

public final class RestaurantFacade {
    private final OrderTracker tracker = new OrderTracker();
    private final Map<String, Order> orders = new HashMap<>();
    private final Map<String, String> statuses = new HashMap<>();
    public RestaurantFacade() {
        tracker.subscribe(new CustomerNotifier());
        tracker.subscribe(new KitchenNotifier());
    }
    public void addObserver(OrderObserver observer) { tracker.subscribe(observer); }
    public BigDecimal placeOrder(Order order, DiscountStrategy discount) {
        Objects.requireNonNull(order); Objects.requireNonNull(discount);
        if (orders.containsKey(order.id())) throw new IllegalArgumentException("Duplicate order id");
        BigDecimal total = Objects.requireNonNull(discount.apply(order.subtotal()));
        if (total.signum() < 0 || total.compareTo(order.subtotal()) > 0) throw new IllegalArgumentException("Invalid total");
        orders.put(order.id(), order);
        statuses.put(order.id(), "PLACED");
        tracker.update(order.id(), "PLACED");
        return total;
    }
    public void changeStatus(String id, String status) {
        if (!orders.containsKey(id)) throw new IllegalArgumentException("Unknown order id");
        String previous = statuses.get(id);
        Map<String, String> next = Map.of("PLACED", "PREPARING", "PREPARING", "READY", "READY", "COMPLETED");
        if (!Objects.equals(next.get(previous), status)) throw new IllegalArgumentException("Invalid status transition");
        statuses.put(id, status);
        tracker.update(id, status);
    }
    public String status(String id) { return statuses.get(id); }
}
