package com.example.restart.NewFeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restart.NewFeedModel;
import com.example.restart.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    List<NewFeedModel> newFeedModelList;




    public MyAdapter(Context context, List<NewFeedModel> newFeedModelList) {
        this.context = context;
        this.newFeedModelList = newFeedModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.newfeedlayout, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.userProfile.setImageBitmap(newFeedModelList.get(position).getUserProfile());
        holder.txtFullName.setText(newFeedModelList.get(position).getUserFullName());
        holder.txtPhone.setText(newFeedModelList.get(position).getPhoneNumber());
        holder.txtPropertyId.setText(String.valueOf(newFeedModelList.get(position).getPropertyID()));
        holder.txtPropertyType.setText(newFeedModelList.get(position).getPropertyType());
        holder.txtBedroomType.setText(newFeedModelList.get(position).getBedroomType());
        holder.txtPrice.setText(newFeedModelList.get(position).getPricePerMonth());
        holder.txtFurniture.setText(newFeedModelList.get(position).getFurnitureType());
        holder.txtRemark.setText(newFeedModelList.get(position).getRemark());
        holder.txtDate.setText(newFeedModelList.get(position).getUploadedDateTime());
        holder.propertyImage.setImageBitmap(newFeedModelList.get(position).getUserUploadedImage());



    }

    @Override
    public int getItemCount() {
        return newFeedModelList.size();
    }
}
