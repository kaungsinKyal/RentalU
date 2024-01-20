package com.example.restart;

import java.util.UUID;

public class UserModel {
    private static int counter = 0; // Static counter for UserID
    private int userID;
    private String Username;
    private String Password;
    private String conPassword;
    private String FullName;
    private String phone;



    private byte[] photo;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }



    public UserModel(String username, String password, String conPassword, String fullName,String phone) {
        this.userID = generateUserID();
        Username = username;
        Password = password;
        this.conPassword = conPassword;
        FullName = fullName;
        this.phone = phone;


    }
    public UserModel(String FullName,byte[] Photo)
    {

        this.photo = Photo;
        this.FullName = FullName;

    }
    public int getUserID(){return this.userID;}

    public int generateUserID() {
        return UUID.randomUUID().hashCode();
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConPassword() {
        return conPassword;
    }

    public void setConPassword(String conPassword) {
        this.conPassword = conPassword;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }





}
