package com.example.restart.MyWall;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restart.R;

public class MyWallViewHoler extends RecyclerView.ViewHolder {
    ImageView userProfile,propertyImage;
    TextView txtFullName,txtPhone,txtPropertyId,txtPropertyType,txtBedroomType,txtPrice,txtFurniture,txtRemark,txtDate;
    public MyWallViewHoler(@NonNull View itemView) {
        super(itemView);
        userProfile = itemView.findViewById(R.id.userProfile);
        txtFullName = itemView.findViewById(R.id.userFullName);
        txtPhone = itemView.findViewById(R.id.userPhone);
        propertyImage = itemView.findViewById(R.id.propertyImage);
        txtPropertyId = itemView.findViewById(R.id.dataPropertyID);
        txtPropertyType = itemView.findViewById(R.id.dataPropertyType);
        txtBedroomType = itemView.findViewById(R.id.dataBedroom);
        txtPrice = itemView.findViewById(R.id.dataPrice);
        txtFurniture = itemView.findViewById(R.id.dataFurniture);
        txtRemark = itemView.findViewById(R.id.dataRemark);
        txtDate = itemView.findViewById(R.id.dataDate);
    }
}
