package com.foodordering.services;

import com.foodordering.dao.MenuDao;
import com.foodordering.entity.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuServices {

    private final MenuDao menuDao;
    private Scanner sc = new Scanner(System.in);

    public MenuServices() {
        this.menuDao = new MenuDao();
    }

    // Create a new menu
    public void createMenu() {
        System.out.println();
        System.out.print("Enter the MENU Name: ");
        String menuName = sc.nextLine();
        System.out.println("___________________________________________________");
        Menu menu = new Menu(menuName, new ArrayList<>());
        menuDao.saveMenu(menu);
        System.out.println();
        System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
        System.out.println("|      Menu Created Successfully to the Menu      |");
        System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
        System.out.println();
    }

    // Retrieve a menu by its ID
    public Menu getMenu() {
        System.out.println();
        System.out.print("Enter the MENU Id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("___________________________________________________");
        return menuDao.getMenu(id);
    }

    // Retrieve all menus
    public List<Menu> getAllMenus() {
        return menuDao.getAllMenus();
    }

    // Update an existing menu
    public void updateMenu(Menu menu) {
        menuDao.updateMenu(menu);
    }

    // Delete a menu by its ID
    public void deleteMenu(int id) {
        menuDao.deleteMenu(id);
    }

    public void displayAllMenus() {
        
        // Get the list of menus from the service
        List<Menu> menuList = this.getAllMenus();

        if (menuList.isEmpty()) {
            System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
            System.out.println("|    No Menu Found!, Please Add Menu First....    |");
            System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
        } else {
            System.out.println("|=================================================|");
            System.out.println("|                Available Menus                  |");
            System.out.println("|=================================================|");

            // Loop through the list and display each menu's details
            for (Menu menu : menuList) {
                System.out.println("\tMenu ID: " + menu.getId());
                System.out.println("\tMenu Name: " + menu.getMenuName());

                // If the Menu class has more properties, display them as well
                // Example:
                // System.out.println("Menu Description: " + menu.getDescription());

                System.out.println("------------------------------------------");
            }
        }
    }

}
