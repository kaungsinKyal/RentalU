package com.example.restart;

import android.content.Context;
import android.widget.Toast;

public class ValidationHelpr {
    public ValidationHelpr(Context context) {
        this.context = context;
    }

    private Context context;
    public boolean validateFullName(String fullName)
    {
        if(fullName.isEmpty()){
            Toast.makeText(context, "Full Name field is required.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (fullName.matches(".*\\d.*")) {
            Toast.makeText(context, "Full Name cannot contain digits.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }
    public boolean validatePhone(String phone)
    {
        if(phone.isEmpty()){
            Toast.makeText(context, "Phone number is required.", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Regular expression to check if the phone number starts with 9 and is followed by 9 digits
        if (!phone.matches("^9\\d{9}$")) {
            Toast.makeText(context, "Invalid phone number format. Please enter a valid 10-digit number starting with 9.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    public boolean validateUsername(String username){
        if(username.isEmpty()){
            Toast.makeText(context, "Username field is required.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(username.length() < 5){
            Toast.makeText(context, "Username must contain any 5 or more characters.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public boolean validatePassword(String password){
        if(password.isEmpty()){
            Toast.makeText(context, "Password field is required.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.length() < 5){
            Toast.makeText(context, "Password must contain any 5 or more characters.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public boolean MatchPassword(String p1, String p2){
        if(!p1.equals(p2.toString())){
            Toast.makeText(context, "Password and confirm password does not match.", Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;
    }
    public boolean cHeckAll(boolean fullname,boolean username,boolean password,boolean p2,boolean phone){
        if(fullname && username && password && p2 && phone){
            return true;
        }
        return false;
    }
}
