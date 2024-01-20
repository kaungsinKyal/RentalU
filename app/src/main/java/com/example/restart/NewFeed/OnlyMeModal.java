package com.example.restart.NewFeed;

import android.graphics.Bitmap;

public class OnlyMeModal {
    private int propertyID;
    private String propertyType;
    private String bedroomType;
    private String pricePerMonth; // Don't forget // Float datatype in database;
    private String furnitureType;
    private String remark;
    private String uploadedDateTime;
    private String userFullName;// table_user;
    private String phoneNumber;// From table_user
    private Bitmap UserProfile;
    private Bitmap userUploadedImage;

    public OnlyMeModal(int propertyID, String propertyType, String bedroomType, String pricePerMonth, String furnitureType, String remark, String uploadedDateTime, String userFullName, String phoneNumber, Bitmap userProfile, Bitmap userUploadedImage) {
        this.propertyID = propertyID;
        this.propertyType = propertyType;
        this.bedroomType = bedroomType;
        this.pricePerMonth = pricePerMonth;
        this.furnitureType = furnitureType;
        this.remark = remark;
        this.uploadedDateTime = uploadedDateTime;
        this.userFullName = userFullName;
        this.phoneNumber = phoneNumber;
        UserProfile = userProfile;
        this.userUploadedImage = userUploadedImage;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
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

    public String getUploadedDateTime() {
        return uploadedDateTime;
    }

    public void setUploadedDateTime(String uploadedDateTime) {
        this.uploadedDateTime = uploadedDateTime;
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
}
