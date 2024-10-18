package com.foodordering.services;

import com.foodordering.dao.UserDao;
import com.foodordering.entity.Address;
import com.foodordering.entity.Cart;
import com.foodordering.entity.User;
import java.util.*;

public class UserServices {

    private UserDao userDao = new UserDao();
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
        System.out.print(" Enter Your Full Name: ");
        String userName = sc.nextLine();
        System.out.println();

        System.out.print(" Enter Your Email: ");
        String email = sc.nextLine();
        System.out.println();

        System.out.print(" Enter Your Contact No.: ");
        String phone = sc.nextLine();
        System.out.println();
        
        System.out.print(" Create Your Password: ");
        String password = sc.nextLine();
        System.out.println();
        
        System.out.print(" Enter your Country: ");
        String country = sc.nextLine();
        System.out.println();
        
        System.out.print(" Enter Your state: ");
        String state = sc.nextLine();
        System.out.println();
        
        System.out.print(" Create Your city: ");
        String city = sc.nextLine();
        System.out.println();
        
        System.out.print(" Enter your pincode: ");
        String pinCode = sc.nextLine();
        System.out.println();
        
        System.out.print(" Enter your street: ");
        String street = sc.nextLine();
        System.out.println();
        
        System.out.println("_______________________________________");

        Address address = new Address(city, country, state, pinCode, street);
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        Cart cart = new Cart(new ArrayList<>(), 0.0);
        User user = new User(userName, phone, password, email, addresses, cart);

        cart.setUser(user);
        userDao.saveUser(user);

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
        System.out.print(" ==> Enter Your Email: ");
        String email = sc.nextLine();
        System.out.println();
        
        System.out.print(" ==> Enter Your Password: ");
        String password = sc.nextLine();
        System.out.println();
        
        System.out.println("___________________________________________________");

        User user = userDao.getUserByEmailAndPassword(email, password);

        if (user != null) {

            System.out.println("|=============================================|");
            System.out.println("   Login successful! Welcome " + user.getUserName());
            System.out.println("|=============================================|");
            return user.getUserId();
        } else {
            System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
            System.out.println("| Invalid username or password. Please try again. |");
            System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
            return -1;
        }
    }

}
