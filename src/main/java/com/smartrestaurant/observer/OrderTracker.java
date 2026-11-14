package com.smartrestaurant.observer;

import java.util.*;

public final class OrderTracker {
    private final List<OrderObserver> observers = new ArrayList<>();
    public void subscribe(OrderObserver observer) { observers.add(Objects.requireNonNull(observer)); }
    public void unsubscribe(OrderObserver observer) { observers.remove(observer); }
    public void update(String id, String status) {
        for (OrderObserver observer : List.copyOf(observers)) observer.onStatusChanged(id, status);
    }
}
