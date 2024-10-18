package com.foodordering;

// import com.foodordering.entity.CartItem;
import com.foodordering.entity.MenuItem;
// import com.foodordering.entity.User;
import com.foodordering.services.UserServices;
import com.foodordering.util.CartUI;
import com.foodordering.services.CartServices;
import com.foodordering.services.MenuItemServices;
import com.foodordering.services.OrderServices;

// import com.foodordering.util.HibernateUtil;
// import org.hibernate.Session;
import java.util.Scanner;

public class CustomerUI {
    public static void main(String[] args) {

        UserServices userServices = new UserServices();
        MenuItemServices menuItemServices = new MenuItemServices();
        CartServices cartServices = new CartServices();
        OrderServices orderServices = new OrderServices();
        CartUI cartUI = new CartUI();
        Scanner sc = new Scanner(System.in);
        boolean isLoggedIn = false;
        int userId = -1;

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

                System.out.println();
                System.out.println("|=========================================|");

                switch (choice) {
                    case 1:
                        userId = userServices.login();
                        isLoggedIn = (userId != -1);
                        break;
                    case 2:
                        userServices.signUp();
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
                System.out.println("|+++++++++++++++++++++++++++++++++++++++++|");
                System.out.println("|--------------- HOME PAGE ---------------|");
                System.out.println("|>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>|");
                System.out.println("|=========================================|");
                System.out.println();

                System.out.println("|=========================================|");
                System.out.println("|       |__________________________|      |");
                System.out.println("|       |1. VIEW MENU              |      |");
                System.out.println("|       |2. VIEW ITEM's BY MENU ID |      |");
                System.out.println("|       |3. ADD ITEMS IN CART      |      |");
                System.out.println("|       |4. VIEW CART              |      |");
                System.out.println("|       |5. VIEW ORDER HISTORY     |      |");
                // System.out.println("|       |6. VIEW ORDER STATUS      |      |");
                // System.out.println("| |7. ACCEPT ORDER | |");
                System.out.println("|       |0. LOG-OUT                |      |");
                System.out.println("|       |__________________________|      |");
                System.out.println("|=========================================|");

                System.out.print("    Choose an option: ");

                int choice = sc.nextInt();
                sc.nextLine();
                System.out.println();
                System.out.println("|=========================================|");

                switch (choice) {
                    case 1:
                        menuItemServices.displayAllMenusItems();
                        break;
                    case 2:
                        menuItemServices.displayAllMenuItemsByMenuId();

                        break;
                    case 3:
                        System.out.println("Enter Item-Id: ");
                        int menuItemId = sc.nextInt();
                        sc.nextLine();
                        MenuItem menuItem = menuItemServices.getMenuItem(menuItemId);
                        System.out.println("Enter Quantity: ");
                        int itemQuantity = sc.nextInt();
                        sc.nextLine();
                        cartServices.addItemToCart(userId, menuItem, itemQuantity);
                        System.out.println("Your Item is Added successfully in Cart!");
                        break;

                    case 4:
                        cartUI.home(userId);
                        break;

                    case 5:
                        orderServices.displayAllOrdersByUserId(userId);
                        break;

                    case 0:
                        System.out.println("You are going to logout.\nare you sure?\n Enter\n 0. Yes\n 1. No ");
                        boolean flag = (sc.nextInt()==0)?true:false;
                        if (flag) {
                            isLoggedIn = false;
                            userId = -1;
                        }
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
    }
}
