/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotv2jay;

/**
 *
 * @author James
 */
public class Student {
    
    //attributes
    private String name;
    private String address;
    private int phone;
    private String email;
    private String password;

    //constructor
    public Student(String userName, String userAddress, String userPhone, String userEmail, String userPassword) {
        name = userName;
        address = userAddress;
        phone = Integer.parseInt(userPhone);
        email = userEmail;
        password = userPassword;
    }
    
    //accessors and mutators

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    
}
