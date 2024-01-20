package com.example.restart.fragmentViews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.restart.DeleteOpenDialog;
import com.example.restart.EditOpenDialog;
import com.example.restart.R;


public class DeleteFragment extends Fragment {
    Button EditOpenDialog,DeleteOpenDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        EditOpenDialog = view.findViewById(R.id.btnEditOpenDialog);
        DeleteOpenDialog = view.findViewById(R.id.btnDeleteOpenDialog);
        EditOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogEdit();
            }
        });
        DeleteOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement later;
                openDialogDelete();

            }
        });
        return view;
    }
    public void openDialogEdit(){
        // Implement later;
        EditOpenDialog editOpenDialog = new EditOpenDialog();
        editOpenDialog.show(getActivity().getSupportFragmentManager(), "Edit");


    }
    public void openDialogDelete()
    {
        DeleteOpenDialog deleteOpenDialog = new DeleteOpenDialog();
        deleteOpenDialog.show(getActivity().getSupportFragmentManager(), "Delete");
        // later;
    }
}