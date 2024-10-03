package com.foodordering.entity;

// import jakarta.persistence.*;
import javax.persistence.*;


// @Table(name = "users")
@Entity
public class User{
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int userId;
    
    // @Column(nullable = false)
    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String userPhone;

    @Column(nullable = false)
    private String password;


    public User(){}

    public User(String userName, String userPhone, String password )
    {
        this.userName = userName;
        this.userPhone= userPhone;
        this.password = password;
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

    
    public String toString(){
        return "User:{"+
            "\nuserId: "+this.userId+
            "\nuserName: "+this.userName+
            "\nuserPhone: "+this.userPhone+
            "\npassword: "+this.password+
        "\n}";
    }

}
