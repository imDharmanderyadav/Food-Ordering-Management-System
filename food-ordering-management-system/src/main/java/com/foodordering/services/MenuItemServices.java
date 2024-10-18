package com.foodordering.services;

import com.foodordering.dao.MenuItemDao;
import com.foodordering.entity.Menu;
import com.foodordering.entity.MenuItem;
import java.util.List;
import java.util.Scanner;

import java.util.*;

public class MenuItemServices {

    private final MenuItemDao menuItemDao;
    private Scanner sc;

    public MenuItemServices() {
        this.menuItemDao = new MenuItemDao();
        this.sc = new Scanner(System.in);
    }

    // Create a new menu item
    public void addMenuItem() {

        System.out.println();
        System.out.print("Enter the menu-item Name: ");
        String menuItemName = sc.nextLine();

        System.out.println();
        System.out.print("Enter the " + menuItemName + " price: ");
        double itemPrice = sc.nextDouble();
        sc.nextLine();

        System.out.println();
        System.out.print("Enter the menu Id: ");
        int menuId = sc.nextInt();
        sc.nextLine();
        System.out.println("___________________________________________________");

        Menu menu = new Menu();
        menu.setId(menuId);

        MenuItem menuItem = new MenuItem(menuItemName, itemPrice, menu);
        menuItemDao.saveMenuItem(menuItem);
        System.out.println();
        System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
        System.out.println("|     Menu Item Added successfully to the Menu    |");
        System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
        System.out.println();
    }

    // Retrieve a menu item by its ID
    public MenuItem getMenuItem(int id) {
        return menuItemDao.getMenuItem(id);
    }

    // Retrieve all menu items
    public List<MenuItem> getAllMenuItems() {
        return menuItemDao.getAllMenuItems();
    }

    // Update an existing menu item
    public void updateMenuItem(MenuItem menuItem) {
        menuItemDao.updateMenuItem(menuItem);
    }

    // Delete a menu item by its ID
    public void deleteMenuItem(int id) {
        menuItemDao.deleteMenuItem(id);
    }

    public void displayAllMenusItems() {
    List<MenuItem> menuItems = getAllMenuItems();

    if (menuItems.isEmpty()) {
        System.out.println("|xx xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
        System.out.println("|        No Item Found in Menu!,         |");
        System.out.println("|        Please Add Item First..         |");
        System.out.println("|xx xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
    } else {
        System.out.println("|========================================|");
        System.out.println("|        Available Items in Menu         |");
        System.out.println("|========================================|");

        // Use a Map to group items by Menu ID
        Map<Menu, List<MenuItem>> menuMap = new HashMap<>();

        // Group the menu items by their associated Menu
        for (MenuItem menuItem : menuItems) {
            Menu menu = menuItem.getMenu();

            // Add the item to the corresponding menu in the map
            menuMap.computeIfAbsent(menu, k -> new ArrayList<>()).add(menuItem);
        }

        // Now, iterate over the map and display the items grouped by Menu
        for (Map.Entry<Menu, List<MenuItem>> entry : menuMap.entrySet()) {
            Menu menu = entry.getKey();
            List<MenuItem> items = entry.getValue();

            System.out.println(".......................................");
            System.out.println(">>\t Menu Id : " + menu.getId());
            System.out.println(">>\t Menu Name : " + menu.getMenuName());
            System.out.println(".......................................");

            // Display all items under this menu
            for (MenuItem item : items) {
                System.out.println();
                System.out.println("\tItem ID: " + item.getId());
                System.out.println("\tItem Name: " + item.getItemName());
                System.out.println("\tItem Price: " + item.getPrice());
                System.out.println();
                System.out.println("      <~~~~~~~~~~~~~~~~~~~~>           ");
            }
            System.out.println();
            System.out.println("=======================================");
        }
    }
}




/*
    public void displayAllMenusItems() {
        List<MenuItem> menuItems = getAllMenuItems();

        if (menuItems.isEmpty()) {
            System.out.println("|xx xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
            System.out.println("|        No Item Found in Menu!,         |");
            System.out.println("|        Please Add Item First..         |");
            System.out.println("|xx xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
        } else {
            System.out.println("|========================================|");
            System.out.println("|        Available Items in Menu         |");
            System.out.println("|========================================|");

            for (MenuItem menuItem : menuItems) {

                Menu menu = menuItem.getMenu();
                System.out.println(".......................................");
                System.out.println(">>\t Menu Id : " + menu.getId());
                System.out.println(">>\t Menu Name : " + menu.getMenuName());
                System.out.println(".......................................");

                System.out.println("\tItem ID: " + menuItem.getId());
                System.out.println("\tItem Name: " + menuItem.getItemName());
                System.out.println("\tItem Price: " + menuItem.getPrice());
                
                System.out.println("---------------------------------------");
            }
        }
    }

    */
    public void displayAllMenuItemsByMenuId() {
        System.out.println("Enter the menu Id: ");
        int menuId = sc.nextInt();
        sc.nextLine();

        List<MenuItem> menuItems = this.menuItemDao.getAllMenuItemsByMenuId(menuId);

        if (menuItems.isEmpty()) {
            System.out.println("|xx xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
            System.out.println("|        No Item Found in Menu!,         |");
            System.out.println("|        Please Add Item First..         |");
            System.out.println("|xx xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
        } else {
            System.out.println("|========================================|");
            System.out.println("|        Available Items in Menu         |");
            System.out.println("|========================================|");

            Menu menu = menuItems.get(0).getMenu();
            System.out.println("..........................................");
            System.out.println(">>\t Menu Id : " + menu.getId());
            System.out.println(">>\t Menu Name : " + menu.getMenuName());
            System.out.println("..........................................");
            for (MenuItem menuItem : menuItems) {
                System.out.println("\tItem ID: " + menuItem.getId());
                System.out.println("\tItem Name: " + menuItem.getItemName());
                System.out.println("\tItem Price: " + menuItem.getPrice());
                System.out.println("---------------------------------------");
            }
        }
    }
}
