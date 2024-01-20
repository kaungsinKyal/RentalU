package com.example.restart.NewFeed;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restart.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView userProfile,propertyImage;
    TextView txtFullName,txtPhone,txtPropertyId,txtPropertyType,txtBedroomType,txtPrice,txtFurniture,txtRemark,txtDate;
    TextView displayID;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        displayID = itemView.findViewById(R.id.displayPropertyID);
        displayID.setVisibility(View.INVISIBLE);
        userProfile = itemView.findViewById(R.id.userProfile);
        txtFullName = itemView.findViewById(R.id.userFullName);
        txtPhone = itemView.findViewById(R.id.userPhone);
        propertyImage = itemView.findViewById(R.id.propertyImage);
        txtPropertyId = itemView.findViewById(R.id.dataPropertyID);
        txtPropertyId.setVisibility(View.INVISIBLE); // how to hide??
        txtPropertyType = itemView.findViewById(R.id.dataPropertyType);
        txtBedroomType = itemView.findViewById(R.id.dataBedroom);
        txtPrice = itemView.findViewById(R.id.dataPrice);
        txtFurniture = itemView.findViewById(R.id.dataFurniture);
        txtRemark = itemView.findViewById(R.id.dataRemark);
        txtDate = itemView.findViewById(R.id.dataDate);
    }
}
