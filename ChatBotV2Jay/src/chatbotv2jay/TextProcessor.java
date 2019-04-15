/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotv2jay;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author James
 */
class TextProcessor {

    //methods
    
    //method to validate that the phone number has 7 or 8 digits and is composed of numerical values only
    boolean validatePhone(String userPhone) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean isNum = false;
        
        if(userPhone.length()==7||userPhone.length()==8)
        {
            try{
                Integer.parseInt(userPhone);
                isNum = true;
            } 
            catch(NumberFormatException e){
                throw new RuntimeException(e); 
            }
        }
        
        return isNum;
    }
    
    //method to validate the email address and if it contains the character @ and a dot near the end of the address
    boolean validateEmail(String userEmail) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        boolean isEmail = false;
        
        if (userEmail.length()>5){
            if (userEmail.charAt(userEmail.length()-3)=='.'||userEmail.charAt(userEmail.length()-4)=='.'){
                char[] array = userEmail.toCharArray();
                for (char i : array){
                    if(i=='@'){
                        isEmail = true;
                    }
                }
            }
        }
        
        return isEmail;
    }

    String encryptPassword(String userPassword) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            MessageDigest convertorMD5 = MessageDigest.getInstance("MD5"); //creating an instance of the convertor
            byte[] passwordInByte = convertorMD5.digest(userPassword.getBytes()); //hashing the password in a byte array
            BigInteger passwordInBigInteger = new BigInteger(1,passwordInByte); // converting in BigInteger
            String passwordInHex = passwordInBigInteger.toString(); //converting into a hex number
            return passwordInHex;
        }
        
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
    
}
