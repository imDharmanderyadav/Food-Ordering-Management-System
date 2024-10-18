package com.foodordering.services;

import java.util.Date;
import java.util.Scanner;

import com.foodordering.entity.Order;
import com.foodordering.entity.Payment;

public class PaymentServices {
    Scanner sc;

    public PaymentServices() {
        sc = new Scanner(System.in);
    }

    public Payment makePayment(Order order) {

        Payment payment = order.getPayment();

        while (payment == null) {

            System.out.println("|=========================================|");
            System.out.println("|+++++++++++++++++++++++++++++++++++++++++|");
            System.out.println("|----------------MAKE PAYMENT ------------|");
            System.out.println("|>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>|");
            System.out.println("|=========================================|");
            System.out.println();


            System.out.println();
            System.out.println("|=========================================|");
            System.out.println("        PAYMENT AMOUNT: " + order.getTotalAmount());
            System.out.println("|=========================================|");

            System.out.println("|=========================================|");
            System.out.println("|       |__________________________|      |");
            System.out.println("|       |     PAYMENT METHOD       |      |");
            System.out.println("|       |1. CASH ON DELIVARY       |      |");
            System.out.println("|       |2. UPI                    |      |");
            System.out.println("|       |3. CANCLE PAYMENT         |      |");
            System.out.println("|       |__________________________|      |");
            System.out.println("|=========================================|");
            System.out.print("    Choose Payment Method: ");

            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println();
            System.out.println("|=========================================|");

            switch (choice) {
                case 1:
                    payment = new Payment(order.getTotalAmount(), "CASH", "Pending", new Date(), order);
                    break;
                case 2:
                    payment = new Payment(order.getTotalAmount(), "UPI", "Completed", new Date(), order);
                    break;

                case 3:
                return null;

                default:
                    payment = null;
                    System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
                    System.out.println("|       Invalid choise!, Please Try again....     |");
                    System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
                    break;
            }

            System.out.println();

        }
        return payment;
    }
}
