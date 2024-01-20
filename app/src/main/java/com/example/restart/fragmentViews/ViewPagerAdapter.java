package com.example.restart.fragmentViews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private int userID;
    private int fragmentCount;
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity,int userID,int fragmentCount) {
        super(fragmentActivity);
        this.userID = userID;
        this.fragmentCount = fragmentCount;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle args = new Bundle();
        args.putInt("userID", userID);
         // userID these is not
       switch(position)
       {
           case 0:
               profile_fragment profile = new profile_fragment();
               profile.setArguments(args);
               return profile;
           case 1:
               newfeed_fragment nefeed = new newfeed_fragment();
               nefeed.setArguments(args);
               return nefeed;
           case 2:
               UploadFragment upload = new UploadFragment();
               upload.setArguments(args);
               return upload;
           case 3:
               EditFragment edit = new EditFragment();
               edit.setArguments(args);
               return edit;
           case 4:
               DeleteFragment delete = new DeleteFragment();
               delete.setArguments(args);
               return delete;
           default:
               profile_fragment pp = new profile_fragment();
               pp.setArguments(args);
               return pp;
       }
    }

    @Override
    public int getItemCount() {
        return fragmentCount;
    }
}
