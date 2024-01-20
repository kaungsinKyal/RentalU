package com.example.restart.fragmentViews;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.restart.DbHelper;
import com.example.restart.ImageHelper;
import com.example.restart.PropertyModel;
import com.example.restart.R;
import com.example.restart.UserModel;
import com.example.restart.ValidationProperty;


public class UploadFragment extends Fragment {
    private static int userID;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ActivityResultLauncher<Intent> galleryLauncher;
    ImageView uploadImageDisplay;
    Button btnSelectImage,btnUpload;
    DbHelper helper;

    ValidationProperty validationProperty = new ValidationProperty();

    EditText propertyType,bedroomType,pricePerMonth,furnitureType,remarkType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload, container, false);
        uploadImageDisplay = view.findViewById(R.id.displayPhoto);
        btnSelectImage = view.findViewById(R.id.btnSelectImage);
        btnUpload = view.findViewById(R.id.btnUpload);
        propertyType = view.findViewById(R.id.inputPropertyType);
        bedroomType = view.findViewById(R.id.inputBedroomType);
        pricePerMonth = view.findViewById(R.id.inputPrice);
        furnitureType = view.findViewById(R.id.inputFurniture);
        remarkType = view.findViewById(R.id.inputRemark);
        helper = new DbHelper(requireContext());
        pricePerMonth.setText("0");
        initializeGalleryLauncher();
        Bundle args = getArguments();
        if (args != null) {
            userID = args.getInt("userID", -1);
        }
        btnSelectImage.setOnClickListener(v -> openGallery());
        btnUpload.setOnClickListener(v -> ActionForBtnUpload());
        return view;
    }
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        galleryLauncher.launch(galleryIntent);
    }
    private void initializeGalleryLauncher() {
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // Handle the result here
                            Intent data = result.getData();
                            // Now you can use the selected image URI
                            if (data != null && data.getData() != null) {
                                // Do something with the selected image URI
                                handleSelectedImage(data.getData());
                            }
                        }
                    }
                }
        );
    }
    private void handleSelectedImage(Uri selectedImageUri) {
        uploadImageDisplay.setImageURI(selectedImageUri);
        // Handle the selected image URI, e.g., set it to an ImageView
    }
    private void ActionForBtnUpload()
    {
        Drawable drawable = uploadImageDisplay.getDrawable();
        if(drawable instanceof BitmapDrawable)
        {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            byte[] bitImage = ImageHelper.bitmapToByteArray(bitmap);
            String Property_Type = propertyType.getText().toString();
            String Bedroom_Type = bedroomType.getText().toString();
            String Price_Per_Month = pricePerMonth.getText().toString();
            float Price = Float.parseFloat(Price_Per_Month);

            String Furniture_Type = furnitureType.getText().toString();
            String Remark = remarkType.getText().toString();
            boolean isValidProperty = validationProperty.ValidateProperty(Property_Type);
            boolean isValidBedroom = validationProperty.ValidateBedroom(Bedroom_Type);
            boolean isValidPrice = validationProperty.ValidatePrice(Price_Per_Month);

            boolean Valid = validationProperty.ValidateAll(isValidPrice,isValidBedroom,isValidProperty);
            if(Valid)
            {
                PropertyModel newPropertyModel = new PropertyModel(Property_Type,Bedroom_Type,Price,Furniture_Type,Remark,userID,bitImage);
                boolean result = helper.insertProperty(newPropertyModel);
                if(result){
                    Toast.makeText(requireContext(), "Successfully uploaded.", Toast.LENGTH_SHORT).show();
                    ClearAll();
                }else{
                    Toast.makeText(requireContext(), "Fail to upload.", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(requireContext(), "Check the input fields which are not optional.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void ClearAll()
    {
        propertyType.setText("");
        bedroomType.setText("");
        pricePerMonth.setText("0");
        furnitureType.setText("");
        remarkType.setText("");

    }
}