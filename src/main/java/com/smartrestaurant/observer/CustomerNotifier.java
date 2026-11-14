package com.smartrestaurant.observer;

public final class CustomerNotifier implements OrderObserver {
    @Override public void onStatusChanged(String id, String status) {
        System.out.println("[CUSTOMER] Order " + id + ": " + status);
    }
}
