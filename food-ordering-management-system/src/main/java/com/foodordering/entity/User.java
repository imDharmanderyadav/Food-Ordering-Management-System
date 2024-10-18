package com.foodordering.entity;

// import jakarta.persistence.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User{
    
    @Id //primary key
    @GeneratedValue( strategy = GenerationType.IDENTITY)//enable auto increment
    private int userId;
    
    // @Column(nullable = false)
    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String userPhone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id") // Foreign key in the addresses table
    private List<Address> address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id",  referencedColumnName = "id" )
    Cart cart;

    public User(){}


    // public User(String userName, String userPhone, String password, String email, List<Address> address) {
        
    //     this.userName = userName;
    //     this.userPhone = userPhone;
    //     this.password = password;
    //     this.email = email;
    //     this.address = address;
    //     this.cart = new Cart(new ArrayList<>(),0.0);
    // }



    
    public User(String userName, String userPhone, String password, String email, List<Address> address, Cart cart) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.password = password;
        this.email = email;
        this.address = address;
        this.cart = cart;
    }



    // getters
    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    // setters
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    } 

    public void setEmail(String email) {
        this.email = email;
    }

    
    public void setUserId(int userId) {
        this.userId = userId;
    }

   

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", userPhone=" + userPhone + ", password="
                + password + ", email=" + email + ", address=" + address + "]";
    }


    public List<Address> getAddress() {
        return address;
    }


    public void setAddress(List<Address> address) {
        this.address = address;
    }


    public Cart getCart() {
        return cart;
    }


    public void setCart(Cart cart) {
        this.cart = cart;
    }

    
   

}
