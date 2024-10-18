package com.foodordering;

// import com.foodordering.entity.MenuItem;
// import com.foodordering.entity.Menu;
import com.foodordering.services.AdminServices;
import com.foodordering.services.MenuItemServices;
import com.foodordering.services.MenuServices;
import com.foodordering.services.OrderServices;

// import java.util.List;

import java.util.Scanner;

public class AdminUI {
    public static void main(String[] args) {

        AdminServices adminServices = new AdminServices();
        MenuServices menuServices = new MenuServices();
        MenuItemServices menuItemServices = new MenuItemServices();
        OrderServices orderServices = new OrderServices();

        Scanner sc = new Scanner(System.in);
        boolean isLoggedIn = false;
        int adminId = -1;

        while (true) {
            if (!isLoggedIn) {
                System.out.println("|=========================================|");
                System.out.println("|---------------- HOME PAGE --------------|");
                System.out.println("|=========================================|");
                System.out.println();

                System.out.println("|=========================================|");
                System.out.println("|              |_____________|            |");
                System.out.println("|              |1. Login     |            |");
                System.out.println("|              |2. Signup    |            |");
                System.out.println("|              |3. Exit      |            |");
                System.out.println("|              |_____________|            |");
                System.out.println("|=========================================|");
                System.out.print("    Choose an option: ");

                int choice = sc.nextInt();
                sc.nextLine();
                System.out.println();
                System.out.println("|=========================================|");

                switch (choice) {
                    case 1:
                        adminId = adminServices.login();
                        isLoggedIn = (adminId != -1);
                        break;
                    case 2:
                        adminServices.signUp();
                        break;

                    case 3:
                        System.exit(0);
                        break;

                    default:

                        System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
                        System.out.println("|       Invalid choise!, Please Try again....     |");
                        System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
                        break;
                }

            }

            else {
                System.out.println("|=========================================|");
                System.out.println("|------------ ADMIN DASHBOARD ------------|");
                System.out.println("|+++++++++++++++++++++++++++++++++++++++++|");
                System.out.println("|>>>>>>>> Press Any Key to Logout >>>>>>>>|");
                System.out.println("|=========================================|");
                System.out.println();

                System.out.println("|=========================================|");
                System.out.println("|        |_________________________|      |");
                System.out.println("|        |1. CREATE MENU           |      |");
                System.out.println("|        |2. VIEW ALL MENUS        |      |");
                System.out.println("|        |3. ADD MENU-ITEM         |      |");
                System.out.println("|        |4. VIEW ALL ITEM's       |      |");
                System.out.println("|        |5. VIEW ALL MENU's-ITEM  |      |");
                System.out.println("|        |6. VIEW NEW ORDERS       |      |");
                System.out.println("|        |7. VIEW ALL ORDER        |      |");
                System.out.println("|        |8. ACCEPT ORDER          |      |");
                System.out.println("|        |0. LOG-OUT               |      |");
                System.out.println("|        |_________________________|      |");
                System.out.println("|=========================================|");

                System.out.print("    Choose an option: ");

                int choice = sc.nextInt();
                sc.nextLine();
                System.out.println();
                System.out.println("|=========================================|");

                switch (choice) {
                    case 1:
                        menuServices.createMenu();
                        break;
                    case 2:
                        menuServices.displayAllMenus();

                        break;
                    case 3:
                        menuItemServices.addMenuItem();
                        break;

                    case 4:
                        menuItemServices.displayAllMenuItemsByMenuId();
                        break;

                    case 5:
                        menuItemServices.displayAllMenusItems();
                        break;
                    case 6:
                        orderServices.displayAllPendingOrders();
                        break;
                    case 7:
                        orderServices.displayAllOrders();
                        break;
                    case 8:
                        System.out.print("==> Enter the Order-ID: ");
                        orderServices.acceptOrderById(sc.nextInt());
                        sc.nextLine();
                        break;

                    case 0:
                        isLoggedIn = false;
                        adminId = -1;
                        break;

                    default:

                        System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
                        System.out.println("|       Invalid choise!, Please Try again....     |");
                        System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
                        break;
                }

                System.out.println();
                // System.out.println("|=========================================|");

                // sc.close();
            }
        }
    }

}
