package com.foodordering.services;

import com.foodordering.dao.AdminDao;
import com.foodordering.entity.Admin;
import java.util.*;

public class AdminServices {
    

    private AdminDao adminDao = new AdminDao();
    private final Scanner sc = new Scanner(System.in);

    // p
    // }

    public void signUp() {
        System.out.println();
        System.out.println("|======================================|");
        System.out.println("|------------- SignUp -----------------|");
        System.out.println("|======================================|");
        System.out.println();

        /* =========== take the input from user ===================== */
        System.out.println(" Enter Your User Name: ");
        String userName = sc.nextLine();

        System.out.println(" Enter Your Email: ");
        String email = sc.nextLine();

        System.out.println(" Create Your Password: ");
        String password = sc.nextLine();


        System.out.println("_______________________________________");

       Admin admin = new Admin(userName, email, password);

        adminDao.saveAdmin(admin);

        System.out.println("|========================================|");
        System.out.println("| Signup successful! You can now log in. |");
        System.out.println("|========================================|");

    }

    public int login() {
        System.out.println();
        System.out.println("|======================================|");
        System.out.println("|------------- LogIn -----------------|");
        System.out.println("|======================================|");
        System.out.println();

        /* =========== take the input from user ===================== */
        System.out.println(" Enter Your user name: ");
        String userName = sc.nextLine();

        System.out.println(" Enter Your Password: ");
        String password = sc.nextLine();

        System.out.println("___________________________________________________");

        Admin admin = adminDao.getAdminByUserNameAndPassword(userName, password) ;

        if (admin != null) {

            System.out.println("|=============================================|");
            System.out.println("   Login successful! Welcome " + admin.getUserName());
            System.out.println("|=============================================|");
            return admin.getId();
        } else {
            System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
            System.out.println("| Invalid username or password. Please try again. |");
            System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
            return -1;
        }
    }

}
