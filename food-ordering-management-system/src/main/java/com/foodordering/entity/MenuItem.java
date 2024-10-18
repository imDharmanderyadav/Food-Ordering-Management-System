package com.foodordering.entity;

import javax.persistence.*;

@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private double price;

    @ManyToOne // Many MenuItems can beint to one Menu
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    // Constructors, getters, and setters

    public MenuItem() {}

    public MenuItem(String itemName, double price, Menu menu) {
        this.itemName = itemName;
        this.price = price;
        this.menu = menu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "MenuItem [id=" + id + ", itemName=" + itemName + ", price=" + price + ", menu=" + menu + "]";
    }
}
