package com.example.restart.fragmentViews;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.restart.DbHelper;
import com.example.restart.ImageHelper;
import com.example.restart.NewFeed.MyAdapter;
import com.example.restart.NewFeed.MyViewHolder;
import com.example.restart.NewFeedModel;
import com.example.restart.R;
import com.example.restart.UserModel;

import java.util.List;


public class newfeed_fragment extends Fragment {
    private static int userID;
    RecyclerView recyclerView;

    DbHelper helper;
    MyAdapter adapter;
    List<NewFeedModel> newFeedModelList;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_newfeed_fragment, container, false);
       recyclerView = view.findViewById(R.id.recyclerView);
       recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
       swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
       helper = new DbHelper(requireContext());


        Bundle args = getArguments();
        if (args != null) {
            userID = args.getInt("userID", -1);
            newFeedModelList = helper.getCombinedData();
            adapter = new MyAdapter(requireContext(),newFeedModelList);
            recyclerView.setAdapter(adapter);

        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newFeedModelList.clear();
                newFeedModelList.addAll(helper.getCombinedData());
                adapter.notifyDataSetChanged();;
                swipeRefreshLayout.setRefreshing(false);
            }
        });
       return view;
    }
}