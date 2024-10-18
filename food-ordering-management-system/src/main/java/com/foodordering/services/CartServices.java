package com.foodordering.services;

import com.foodordering.dao.CartDao;
import com.foodordering.dao.UserDao;
import com.foodordering.entity.Cart;
import com.foodordering.entity.CartItem;
import com.foodordering.entity.User;
import com.foodordering.entity.MenuItem;
import java.util.List;
import java.util.ArrayList;

public class CartServices {

    private CartDao cartDao;
    private UserDao userDao;

    // Constructor
    public CartServices() {
        this.cartDao = new CartDao();
        this.userDao = new UserDao();
    }

    // Fetch the user's cart by userId
    public Cart getCartByUserId(int userId) {
        return cartDao.getCartByUserId(userId);
    }

    // Add an item to the user's cart
    public void addItemToCart(int userId, MenuItem menuItem, int quantity) {
        Cart userCart = cartDao.getCartByUserId(userId);
        
        if (userCart == null) {
            // Create a new cart if none exists for the user 
            User user = userDao.getUserById(userId);  // Fetch the user
            user.setCart(new Cart(new ArrayList<>(), 0.0));
            // Associate the cart with the user
            System.err.println("userCart == null condition is run");
            userDao.updateUser(user);  // Save the new cart in DB
            userCart = user.getCart();
        }

        // Add the item to the user's cart
        CartItem cartItem = new CartItem(menuItem, userCart, quantity);
        userCart.getCartItems().add(cartItem);
        double toatalAmount= userCart.getTotalAmount()+quantity*menuItem.getPrice();
        userCart.setTotalAmount(toatalAmount);

        // List<CartItem> cartItems = userCart.getCartItems();
        // for (CartItem cartItem2 : cartItems) {
        //     MenuItem menuItem2 = cartItem2.getMenuItem();
        //     totalAmount = totalAmount
        // }
        
        // Save the cart item (assuming cascading saves cart items)
        cartDao.updateCart(userCart);
    }

    // Remove an item from the user's cart
    public void removeItemFromCart(int userId, int menuItemId) {
        Cart userCart = cartDao.getCartByUserId(userId);
        
        if (userCart != null) {
            List<CartItem> cartItems = userCart.getCartItems();
            
            CartItem itemToRemove = cartItems.stream()
                .filter(cartItem -> cartItem.getMenuItem().getId() == menuItemId)
                .findFirst()
                .orElse(null);
            
            if (itemToRemove != null) {
                cartItems.remove(itemToRemove);
                cartDao.updateCart(userCart);  // Update the cart
            }
        }
    }

    // Get all items from the user's cart
    public List<CartItem> getAllItemsInCart(int userId) {
        Cart userCart = cartDao.getCartByUserId(userId);
        
        if (userCart != null) {
            return userCart.getCartItems();
        }
        return null;  // Return null or empty list if no cart exists for the user
    }

    // Clear the user's cart (e.g., after placing an order)
    public void clearCart(int userId) {
        Cart userCart = cartDao.getCartByUserId(userId);
        
        if (userCart != null) {
            userCart.getCartItems().clear();  // Clear all cart items
            userCart.setTotalAmount(0.0);
            cartDao.updateCart(userCart);  // Update the cart in DB
        }
    }
    
    // Check if user's cart is empty
    public boolean isCartEmpty(int userId) {
        Cart userCart = cartDao.getCartByUserId(userId);
        
        return userCart == null || userCart.getCartItems().isEmpty();
    }

    public void displayCart(int UserId){

        if(isCartEmpty(UserId)){
            System.out.println("Cart is empty!");
        }
        else
        {
            Cart cart = getCartByUserId(UserId);
            List<CartItem> cartItems = cart.getCartItems();
            if(cartItems.isEmpty()){
                System.out.println("Cart is empty! ");
            }
            else
            {
                System.out.println("|=====================================|");
                System.out.println("|==>            YOUR CART          <==|");
                System.out.println("|=====================================|");
                MenuItem menuItem;
                for (CartItem cartItem : cartItems) {

                    System.out.println();
                    System.out.println("      <~~~~~~~~~~~~~~~~~~~~>       ");
                    System.out.println();
                    System.out.println("Cart Item Id: "+cartItem.getCartItemId());
                    menuItem = cartItem.getMenuItem();
                    System.out.println("Item Id: "+menuItem.getId());
                    System.out.println("Item Name: "+ menuItem.getItemName());
                    System.out.println("Quantity: "+ cartItem.getQuantity());
                    System.out.println("....................................");
                    System.out.println("Total Amount: "+ menuItem.getPrice()*cartItem.getQuantity());
                    System.out.println("....................................");
                    System.out.println();
                    System.out.println("      <~~~~~~~~~~~~~~~~~~~~>       ");
                    // grandTotal = grandTotal+menuItem.getPrice()*cartItem.getQuantity();
                  
                }

                System.out.println("|=====================================|");
                System.out.println("|==>Grand Total:      <==|"+cart.getTotalAmount());
                System.out.println("|=====================================|");
            }
        }

    }
}
