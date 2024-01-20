package com.example.restart;

public class ValidationProperty {
    public boolean ValidateProperty(String _property)
    {
        if(!_property.isEmpty()){
            return true;
        }
        return false;
    }
    public boolean ValidateBedroom(String _bedroom)
    {
        if(!_bedroom.isEmpty()){
            return true;
        }
        return false;
    }
    public boolean ValidatePrice(String _price)
    {
        if(!_price.isEmpty()){
            return true;
        }
        return false;
    }
    public boolean ValidateAll(boolean p1,boolean p2, boolean p3)
    {
        if(p1 && p2 && p3)
        {
            return true;
        }
        return false;
    }
    public boolean ValidatePropertyID(String ID)
    {
        if(!ID.isEmpty())
        {
            return true;
        }
        return false;
    }
    public boolean ValidEdit(boolean p1,boolean p2, boolean p3, boolean p4)
    {
        if(p1 && p2 && p3 && p4){
            return true;
        }else{
            return false;
        }
    }
}
