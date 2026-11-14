package com.smartrestaurant.observer;

public final class KitchenNotifier implements OrderObserver {
    @Override public void onStatusChanged(String id, String status) {
        System.out.println("[KITCHEN] Order " + id + ": " + status);
    }
}
