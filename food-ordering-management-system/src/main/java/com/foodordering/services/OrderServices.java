package com.foodordering.services;

import com.foodordering.dao.OrderDao;
import com.foodordering.entity.Order;

import java.util.List;
import java.util.Scanner;

public class OrderServices {

    private OrderDao orderDao;

    public OrderServices() {
        this.orderDao = new OrderDao();
    }

    // Place a new order
    public void placeOrder(Order order) {
        if (order != null && order.getOrderItems() != null && !order.getOrderItems().isEmpty()) {
            orderDao.saveOrder(order);
        } else {
            throw new IllegalArgumentException("Order or Order items cannot be null or empty");
        }
    }

    // Update an existing order
    public void updateOrder(Order order) {
        if (order != null && order.getOrderId() > 0) {
            orderDao.updateOrder(order);
        } else {
            throw new IllegalArgumentException("Invalid order ID or order is null");
        }
    }

    // Cancel an order (delete by ID)
    public void cancelOrder(int orderId) {
        if (orderId > 0) {
            orderDao.deleteOrder(orderId);
        } else {
            throw new IllegalArgumentException("Invalid order ID");
        }
    }

    // Get an order by ID
    public Order getOrderById(int orderId) {
        if (orderId > 0) {
            return orderDao.getOrderById(orderId);
        } else {
            throw new IllegalArgumentException("Invalid order ID");
        }
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public List<Order> getAllOrdersByUserId(int userId) {
        return orderDao.getAllOrdersByUserId(userId);
    }

    public void displayAllOrdersByUserId(int userId) {

        System.out.println("|======================================|");
        System.out.println("|==>      YOUR ORDER HISTORY        <==|");
        System.out.println("|======================================|");
        List<Order> orders = getAllOrdersByUserId(userId);
        System.out.println("\n\n");
        if (orders.isEmpty()) {
            System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
            System.out.println("|             No, Order Placed Yet             |");
            System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
        } else {

            for (Order order : orders) {
                System.out.println("=============================================");
                System.out.println();
                System.out.println("~~>    Order-Id:       " + order.getOrderId());
                System.out.println("~~>    Order-Status:   " + order.getStatus());
                System.out.println("~~>    Order-Amount:   " + order.getTotalAmount());
                System.out.println("~~>    Order-Date:     " + order.getOrderDate());
                System.out.println("~~>    Payment-Status: " + order.getPayment().getPaymentStatus());
                System.out.println("~~>    Payment-Method: " + order.getPayment().getPaymentMethod());
                System.out.println();
                System.out.println("=============================================");
                System.out.println("                   ++++++++++                ");
            }
        }
    }

    public void displayAllOrders() {

        System.out.println("|======================================|");
        System.out.println("|==>            ALL ORDERS          <==|");
        System.out.println("|======================================|");
        List<Order> orders = getAllOrders();
        System.out.println("\n\n");
        if (orders.isEmpty()) {
            System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
            System.out.println("|             No, Order Placed Yet             |");
            System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
        } else {

            for (Order order : orders) {
                System.out.println("=============================================");
                System.out.println();
                System.out.println("~~>    User-Id    :    " + order.getUser().getUserId());
                System.out.println("~~>    User-Email :    " + order.getUser().getEmail());
                System.out.println("~~>    Order-Id:       " + order.getOrderId());
                System.out.println("~~>    Order-Status:   " + order.getStatus());
                System.out.println("~~>    Order-Amount:   " + order.getTotalAmount());
                System.out.println("~~>    Order-Date:     " + order.getOrderDate());
                System.out.println("~~>    Payment-Status: " + order.getPayment().getPaymentStatus());
                System.out.println("~~>    Payment-Method: " + order.getPayment().getPaymentMethod());
                System.out.println();
                System.out.println("=============================================");
                System.out.println("                   ++++++++++                ");
            }
        }
    }

    public void displayAllPendingOrders() {

        System.out.println("|======================================|");
        System.out.println("|==>       ALL PENDING ORDERS       <==|");
        System.out.println("|======================================|");
        List<Order> orders = OrderDao.getAllPendingOrders();
        System.out.println("\n\n");
        if (orders.isEmpty()) {
            System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
            System.out.println("|             No, Order Placed Yet             |");
            System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
        } else {

            for (Order order : orders) {
                System.out.println("=============================================");
                System.out.println();
                System.out.println("~~>    User-Id    :    " + order.getUser().getUserId());
                System.out.println("~~>    User-Email :    " + order.getUser().getEmail());
                System.out.println("~~>    Order-Id:       " + order.getOrderId());
                System.out.println("~~>    Order-Status:   " + order.getStatus());
                System.out.println("~~>    Order-Amount:   " + order.getTotalAmount());
                System.out.println("~~>    Order-Date:     " + order.getOrderDate());
                System.out.println("~~>    Payment-Status: " + order.getPayment().getPaymentStatus());
                System.out.println("~~>    Payment-Method: " + order.getPayment().getPaymentMethod());
                System.out.println();
                System.out.println("=============================================");
                System.out.println("                   ++++++++++                ");
            }
        }
    }

    public void acceptOrderById(int orderId) {
        Scanner sc = new Scanner(System.in);
        Order order = getOrderById(orderId); // Fetch order by its ID

        if (order != null) {
            System.out.println("|=========================================|");
            System.out.println("|        |_________________________|      |");
            System.out.println("|        |=> UPDATE ORDER STATUS <=|      |");
            System.out.println("|        |_________________________|      |");
            System.out.println("|        |1. DELIVERED             |      |");
            System.out.println("|        |2. PLACED                |      |");
            System.out.println("|        |3. PENDING               |      |");
            System.out.println("|        |4. CANCLE                |      |"); 
            System.out.println("|        |0. GO BACK               |      |");
            System.out.println("|        |_________________________|      |");
            System.out.println("|=========================================|");

            System.out.print("    CHOOSE ANY ONE STATUS: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Clear the buffer
            System.out.println();
            System.out.println("|=========================================|");

            switch (choice) {
                case 1:
                    order.setStatus("Delivered");
                    break;
                case 2:
                    order.setStatus("Placed");
                    break;
                case 3:
                    order.setStatus("Pending");
                    break;
                case 4:
                    order.setStatus("Cancle");
                    break;
                case 0:
                    System.out.println("Returning to previous menu...");
                    return;
                default:
                    System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
                    System.out.println("|       Invalid choice!, Please Try again....     |");
                    System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
                    return; 
            }

            updateOrder(order); 
            System.out.println("|=========================================|");
            System.out.println("|   Order Status Updated Successfully!    |");
            System.out.println("|=========================================|");

        } else {
            System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
            System.out.println("|       Order Not Found!, Please Try again....    |");
            System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
        }

    }

}
