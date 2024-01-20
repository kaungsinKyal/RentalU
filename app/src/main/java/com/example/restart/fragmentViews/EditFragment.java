package com.example.restart.fragmentViews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.restart.DbHelper;
import com.example.restart.MyWall.MyWallViewHoler;
import com.example.restart.MyWall.WallViewAdapter;
import com.example.restart.NewFeed.MyAdapter;
import com.example.restart.NewFeed.OnlyMeModal;
import com.example.restart.NewFeedModel;
import com.example.restart.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class EditFragment extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    WallViewAdapter adapter;
    DbHelper helper;
    List<NewFeedModel> newFeedModelList;
    private static int userID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        helper = new DbHelper(requireContext());
        Bundle args = getArguments();

        if (args != null) {
            userID = args.getInt("userID", -1);
            newFeedModelList = helper.getCombinedDataForUser(userID);
            // only find userID related information...
            adapter = new WallViewAdapter(requireContext(),newFeedModelList);
            recyclerView.setAdapter(adapter);

        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newFeedModelList.clear();
                newFeedModelList.addAll(helper.getCombinedDataForUser(userID));
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }
}