package com.smartrestaurant;

import com.smartrestaurant.builder.Order;
import com.smartrestaurant.decorator.*;
import com.smartrestaurant.factory.*;
import com.smartrestaurant.facade.RestaurantFacade;
import com.smartrestaurant.factory.product.Meal;
import com.smartrestaurant.strategy.*;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

public final class Main {

    public static void main(String[] args) {
        RestaurantFacade restaurant = new RestaurantFacade();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== SMART RESTAURANT ===\n1. Place order\n0. Exit");
                String choice = scanner.nextLine().trim();
                if (choice.equals("0")) return;
                if (!choice.equals("1")) { System.out.println("Choose 1 or 0"); continue; }
                try {
                    System.out.print("Customer name: ");
                    String customer = scanner.nextLine().trim();
                    System.out.print("Address (blank for pickup): ");
                    String address = scanner.nextLine().trim();
                    if (address.isEmpty()) address = "Pickup";
                    Order.Builder builder = Order.builder(UUID.randomUUID().toString().substring(0, 8))
                            .customer(customer).address(address);
                    boolean more;
                    do {
                        System.out.print("Meal (1=Pizza 2500 KZT, 2=Burger 1800 KZT): ");
                        String mealChoice = scanner.nextLine().trim();
                        Meal meal = switch (mealChoice) {
                            case "1" -> new PizzaFactory().create();
                            case "2" -> new BurgerFactory().create();
                            default -> throw new IllegalArgumentException("Invalid meal");
                        };
                        System.out.print("Extra cheese +300? (y/n): ");
                        if (scanner.nextLine().trim().equalsIgnoreCase("y")) meal = new CheeseDecorator(meal);
                        System.out.print("Extra sauce +150? (y/n): ");
                        if (scanner.nextLine().trim().equalsIgnoreCase("y")) meal = new SauceDecorator(meal);
                        builder.addMeal(meal);
                        System.out.println("Added: " + meal.description() + " = " + meal.price() + " KZT");
                        System.out.print("Add another meal? (y/n): ");
                        more = scanner.nextLine().trim().equalsIgnoreCase("y");
                    } while (more);
                    Order order = builder.build();
                    System.out.println("Subtotal: " + order.subtotal() + " KZT");
                    System.out.print("Discount (0=None, 1=10%, 2=500 KZT): ");
                    DiscountStrategy strategy = switch (scanner.nextLine().trim()) {
                        case "0" -> new NoDiscount();
                        case "1" -> new PercentageDiscount(10);
                        case "2" -> new FixedDiscount(new BigDecimal("500.00"));
                        default -> throw new IllegalArgumentException("Invalid discount");
                    };
                    BigDecimal total = restaurant.placeOrder(order, strategy);
                    System.out.println("ORDER " + order.id() + " | TOTAL " + total + " KZT");
                    System.out.print("Advance order through kitchen stages? (y/n): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                        for (String status : new String[]{"PREPARING", "READY", "COMPLETED"}) {
                            restaurant.changeStatus(order.id(), status);
                        }
                    }
                } catch (IllegalArgumentException | IllegalStateException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
