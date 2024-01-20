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

public class EditOpenDialog extends AppCompatDialogFragment {
    ValidationProperty validationProperty;
    DbHelper helper;
    private EditText inputPropertyID,inputPropertyType,inputBedroomType,inputPriceType,inputFurniture,inputRemark;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_edit_dialog_view,null);
        validationProperty = new ValidationProperty();
        helper = new DbHelper(requireContext());
        inputPropertyID = view.findViewById(R.id.editPropertyID);
        inputPropertyType = view.findViewById(R.id.editPropertyTye);
        inputBedroomType = view.findViewById(R.id.editBedroomType);
        inputPriceType = view.findViewById(R.id.editPrice);
        inputPriceType.setText("0"); // handling null value of float.;
        inputFurniture = view.findViewById(R.id.editFurniture);
        inputRemark = view.findViewById(R.id.editRemark);
        builder.setView(view)
                .setTitle("Edit your posts")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean notNullID = validationProperty.ValidatePropertyID(inputPropertyID.getText().toString());
                        boolean notNullProperty = validationProperty.ValidateProperty(inputPropertyType.getText().toString());
                        boolean notNullBedroom = validationProperty.ValidateBedroom(inputBedroomType.getText().toString());
                        boolean notNullPrice = validationProperty.ValidatePrice(inputPriceType.getText().toString());
                        if(validationProperty.ValidEdit(notNullPrice,notNullBedroom,notNullProperty,notNullID))
                        {
                            String pID_String = inputPropertyID.getText().toString();
                            int pID = Integer.parseInt(pID_String);
                            String property_type = inputPropertyType.getText().toString();
                            String bedroom_type = inputBedroomType.getText().toString();
                            String price_string = inputPriceType.getText().toString();
                            float price = Float.parseFloat(price_string);
                            String furniture_type = inputFurniture.getText().toString();
                            String remark = inputRemark.getText().toString();
                            boolean result = helper.updateProperty(pID,property_type,bedroom_type,price,furniture_type,remark);
                            if(result){
                                Toast.makeText(requireContext(), "Property ID :" + pID_String + "has been edited successfully.", Toast.LENGTH_SHORT).show();
                                ClearAll();

                            }else{
                                Toast.makeText(requireContext(), "Failed to edit post.", Toast.LENGTH_SHORT).show();
                            }

                            // Update the data;
                        }else{
                            Toast.makeText(requireContext(), "Please check the input fields which are not optional.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        return builder.create();

    }
    private void ClearAll()
    {
        inputPropertyID.setText("");
        inputPropertyType.setText("");
        inputBedroomType.setText("");
        inputPriceType.setText("0");
        inputFurniture.setText("");
        inputRemark.setText("");
    }
}
