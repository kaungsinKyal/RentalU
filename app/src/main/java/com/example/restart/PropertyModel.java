package com.example.restart;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class PropertyModel {
    private int propertyID;
    private String propertyType;
    private String bedroomType;
    private float price;
    private String furniture; // optional
    private String remark; // optional
    private byte[] propertyImage;
    private int foreignKeyUserID; // foreignKey from UserModel;
    private String date;

    public byte[] getPropertyImage() {
        return propertyImage;
    }

    public void setPropertyImage(byte[] propertyImage) {
        this.propertyImage = propertyImage;
    }

    public int getForeignKeyUserID() {
        return foreignKeyUserID;
    }

    public void setForeignKeyUserID(int foreignKeyUserID) {
        this.foreignKeyUserID = foreignKeyUserID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PropertyModel(String propertyType, String bedroomType, float price, String furniture, String remark, int UserID, byte[] propertyImage) {
        this.propertyID = generatePropertyID();
        this.propertyType = propertyType;
        this.bedroomType = bedroomType;
        this.price = price;
        if(furniture.isEmpty()){
            furniture = "Not available";
            this.furniture = furniture;
        }
        if(remark.isEmpty())
        {
            remark = "For more information, contact to owner.";
        }

       this.foreignKeyUserID = UserID;
       this.propertyImage = propertyImage;
       this.date = generateDateString();
    }
    public int getPropertyID(){return this.propertyID;}

    public int generatePropertyID() {
        return UUID.randomUUID().hashCode();
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String generateDateString()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        // Get the current date and time
        Date currentDate = Calendar.getInstance().getTime();

        // Format the date as a string
        String formattedDate = simpleDateFormat.format(currentDate);

        return formattedDate;
    }
}
