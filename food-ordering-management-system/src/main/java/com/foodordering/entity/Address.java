package com.foodordering.entity;


import javax.persistence.*;

@Entity
public class Address {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addId;

    private String city;
    private String country;
    private String state;
    private String pinCode;
    private String street;
 
    public Address(){}

    public Address( String city, String country, String state, String pinCode, String street) {
       
        this.city = city;
        this.country = country;
        this.state = state;
        this.pinCode = pinCode;
        this.street = street;
    }

    public int getAddId() {
        return addId;
    }

    public void setAddId(int addId) {
        this.addId = addId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    @Override
    public String toString() {
        return "Address [addId=" + addId + ", city=" + city + ", country=" + country + ", state=" + state + ", pinCode="
                + pinCode + ", street=" + street + "]";
    }
   
    

    
}
