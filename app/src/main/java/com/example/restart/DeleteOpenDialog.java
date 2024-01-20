package com.example.restart;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DeleteOpenDialog extends AppCompatDialogFragment {
    DbHelper helper;
    ValidationProperty validationProperty;
    EditText inputPropertyID;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_delete_dialog_view,null);
        helper = new DbHelper(requireContext());
        validationProperty = new ValidationProperty();
        inputPropertyID = view.findViewById(R.id.deletePropertyID);
        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       boolean notNullID = validationProperty.ValidateProperty(inputPropertyID.getText().toString());
                       if(notNullID)
                       {
                           String pID_STRING = inputPropertyID.getText().toString();
                           int pID = Integer.parseInt(pID_STRING);
                           boolean IsDeleted = helper.deleteProperty(pID);
                           if(IsDeleted){
                               Toast.makeText(requireContext(), "Property ID :" +pID_STRING+ "has been deleted successfully.", Toast.LENGTH_SHORT).show();
                               inputPropertyID.setText("");

                           }else{
                               Toast.makeText(requireContext(), "Failed to delete" + pID_STRING + "." , Toast.LENGTH_SHORT).show();
                           }


                       }else{
                           Toast.makeText(requireContext(), "Property ID field is empty.", Toast.LENGTH_SHORT).show();
                       }
                        // Delete from table;
                    }
                })
                .setTitle("Delete a post");
        return builder.create();
    }
}
