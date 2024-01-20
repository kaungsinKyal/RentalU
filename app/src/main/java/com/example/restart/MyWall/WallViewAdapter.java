package com.example.restart.MyWall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restart.NewFeed.MyViewHolder;
import com.example.restart.NewFeedModel;
import com.example.restart.R;

import java.util.List;

public class WallViewAdapter extends RecyclerView.Adapter<MyWallViewHoler> {
    Context context;
    List<NewFeedModel> newFeedModelList;

    public WallViewAdapter(Context context, List<NewFeedModel> newFeedModelList) {
        this.context = context;
        this.newFeedModelList = newFeedModelList;
    }

    @NonNull
    @Override
    public MyWallViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyWallViewHoler(LayoutInflater.from(context).inflate(R.layout.mywalllayout, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyWallViewHoler holder, int position) {
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
