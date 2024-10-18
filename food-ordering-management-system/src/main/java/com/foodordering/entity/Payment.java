package com.foodordering.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String paymentMethod;  // e.g., "Credit Card", "PayPal", "Cash"

    @Column(nullable = false)
    private String paymentStatus;  // e.g., "Completed", "Pending", "Failed"

    @Column(nullable = false)
    private Date paymentDate;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId", nullable = false)
    private Order order;  // Relationship to the Order

    public Payment() {
    }

    public Payment(double amount, String paymentMethod, String paymentStatus, Date paymentDate, Order order) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.order = order;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
