package com.foodordering.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.foodordering.entity.Cart;
import com.foodordering.entity.CartItem;
import com.foodordering.entity.Order;
import com.foodordering.entity.OrderItem;
import com.foodordering.entity.Payment;
import com.foodordering.services.CartServices;
import com.foodordering.services.OrderServices;
import com.foodordering.services.PaymentServices;

public class CartUI {

    CartServices cartServices;
    OrderServices orderServices;
    PaymentServices paymentServices;
    Scanner sc;

    public CartUI() {
        cartServices = new CartServices();
        orderServices = new OrderServices();
        paymentServices = new PaymentServices();
        sc = new Scanner(System.in);
    }

    public void home(int userId) {
        if (cartServices.isCartEmpty(userId)) {
            System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
            System.out.println("|                    Cart is Empty                |");
            System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");

        } else {

            cartServices.displayCart(userId);
            System.out.println("|=========================================|");
            System.out.println("|+++++++++++++++++++++++++++++++++++++++++|");
            System.out.println("|----------------- CART ------------------|");
            System.out.println("|>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>|");
            System.out.println("|=========================================|");
            System.out.println();

            System.out.println("|=========================================|");
            System.out.println("|       |__________________________|      |");
            System.out.println("|       |1. PLACE ORDER            |      |");
            System.out.println("|       |2. DELETE ITEM            |      |");
            System.out.println("|       |3. CLEAR CART             |      |");
            System.out.println("|       |4. BACK                   |      |");
            System.out.println("|       |__________________________|      |");
            System.out.println("|=========================================|");

            System.out.print("    Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println();
            System.out.println("|=========================================|");

            switch (choice) {
                case 1:
                    placeOrder(userId);
                    break;
                case 2:
                    // menuItemServices.displayAllMenuItemsByMenuId();

                    break;

                case 3:
                    cartServices.clearCart(userId);
                    System.out.println("=======================================");
                    System.out.println("         Cart is Cleared!..");
                    System.out.println("=======================================");
                    break;

                case 4:
                    break;

                default:

                    System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
                    System.out.println("|       Invalid choise!, Please Try again....     |");
                    System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
                    break;
            }

            System.out.println();
        }

    }

    public void placeOrder(int userId) {
        // Get the cart for the given user
        Cart cart = cartServices.getCartByUserId(userId);

        // Check if the cart is null or empty
        if (cart == null || cart.getCartItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty, cannot place order.");
        }

        // Create a list of OrderItems based on the CartItems
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem(cartItem.getQuantity(), cartItem.getMenuItem().getPrice(), null,
                    cartItem.getMenuItem());
            orderItems.add(orderItem);
        }

        // Create a new Order with the data from the cart
        Order order = new Order(new Date(), "pending", cart.getTotalAmount(), cart.getUser(), orderItems, null);

        // Set the relationship between the order and its order items
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
        }

        Payment payment = paymentServices.makePayment(order);
        if (payment != null) {
            order.setPayment(payment);
            // Save the order in the database
            orderServices.placeOrder(order);

            // Clear the cart after placing the order
            cartServices.clearCart(userId);
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Payment failed!, Please Try Again");
        }
    }
}
