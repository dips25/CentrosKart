package com.example.windows.debcart;

/**
 * Created by Windows on 1/13/2017.
 */
public class Person_details {
    public String name,email,address,password;


    public Person_details()
    {
    }
    public Person_details(String password, String email, String  address, String name)
    {
        this.password=password;
        this.name=name;
        this.address=address;
        this.email=email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
