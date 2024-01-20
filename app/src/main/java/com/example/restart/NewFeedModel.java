package com.example.restart;

import android.graphics.Bitmap;

public class NewFeedModel {
    private int propertyID;

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    private String propertyType;
    private String bedroomType;
    private String pricePerMonth; // Don't forget // Float datatype in database;
    private String furnitureType;
    private String remark;
    private String uploadedDateTime;
    // From Table_Property;

    public int getPropertyID() {
        return propertyID;
    }

    public String getUploadedDateTime() {
        return uploadedDateTime;
    }

    public void setUploadedDateTime(String uploadedDateTime) {
        this.uploadedDateTime = uploadedDateTime;
    }

    // Additional information from Table_User;
    private String userFullName;// table_user;
    private String phoneNumber;// From table_user
    private Bitmap UserProfile;
    private Bitmap userUploadedImage;

    public Bitmap getUserProfile() {
        return UserProfile;
    }

    public void setUserProfile(Bitmap userProfile) {
        UserProfile = userProfile;
    }

    public Bitmap getUserUploadedImage() {
        return userUploadedImage;
    }

    public void setUserUploadedImage(Bitmap userUploadedImage) {
        this.userUploadedImage = userUploadedImage;
    }

    public NewFeedModel(int propertyID, String propertyType, String bedroomType, String pricePerMonth, String furnitureType, String remark, String userFullName, String phoneNumber, String date, Bitmap userProfile, Bitmap userUploadedImage) {
        this.propertyType = propertyType;
        this.bedroomType = bedroomType;
        this.pricePerMonth = pricePerMonth;
        this.furnitureType = furnitureType;
        this.remark = remark;
        this.userFullName = userFullName;
        this.phoneNumber = phoneNumber;
        this.UserProfile = userProfile;
        this.userUploadedImage = userUploadedImage;

        this.propertyID = propertyID;
        this.uploadedDateTime = date;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getBedroomType() {
        return bedroomType;
    }

    public void setBedroomType(String bedroomType) {
        this.bedroomType = bedroomType;
    }

    public String getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(String pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public String getFurnitureType() {
        return furnitureType;
    }

    public void setFurnitureType(String furnitureType) {
        this.furnitureType = furnitureType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }




    }

