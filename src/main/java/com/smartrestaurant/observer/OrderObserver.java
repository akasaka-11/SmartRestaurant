package com.smartrestaurant.observer;

public interface OrderObserver {
    void onStatusChanged(String orderId, String status);
}
