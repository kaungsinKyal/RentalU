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
import android.widget.TextView;
import android.widget.Toast;

import com.example.restart.DbHelper;
import com.example.restart.ImageHelper;
import com.example.restart.LoginView;
import com.example.restart.R;
import com.example.restart.UserModel;


public class profile_fragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1; // Request code for gallery intent
    ImageView profileImage;
    EditText FullNameEDt;
    Button btnSave,btnEdit,btnLogout,btnChooseImage;
    private ActivityResultLauncher<Intent> galleryLauncher;
    private static int userID;

    DbHelper helper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_fragment, container, false);
        profileImage = view.findViewById(R.id.imgViewProfileImage);
        FullNameEDt = view.findViewById(R.id.edtFullName);
        btnSave = view.findViewById(R.id.btnSave);
        btnEdit = view.findViewById(R.id.btnEdit);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnChooseImage = view.findViewById(R.id.btnChooseImage);

        FullNameEDt.setEnabled(false);
        btnSave.setEnabled(false);
        btnChooseImage.setEnabled(false);
        initializeGalleryLauncher();
        btnLogout.setOnClickListener(v -> btnLogout());



        helper = new DbHelper(requireContext());
        btnEdit.setOnClickListener(v -> toggleEditButton());

        // Retrieve userID from arguments
        Bundle args = getArguments();
        if (args != null) {
            userID = args.getInt("userID", -1);
            UserModel updateUser = helper.getUserDataById(userID);
            if(updateUser != null)
            {
                FullNameEDt.setText(updateUser.getFullName());
                byte[] photoData = updateUser.getPhoto();
                if(photoData != null){
                    Bitmap profile = ImageHelper.byteArrayToBitmap(photoData);
                    profileImage.setImageBitmap(profile);
                }else{
                    profileImage.setImageResource(R.mipmap.profilelogo);
                }
            }else{
                profileImage.setImageResource(R.mipmap.profilelogo);
            }



        }
        btnChooseImage.setOnClickListener(v -> openGallery());
        btnSave.setOnClickListener(v -> btnSave());


        // You can perform other UI-related tasks or actions here

        return view;
    }
    private void toggleEditButton() {
        boolean editable = !FullNameEDt.isEnabled(); // Toggle the state

        // Set the enabled state for relevant views
        btnEdit.setEnabled(!editable);
        btnSave.setEnabled(editable);
        btnChooseImage.setEnabled(editable);
        FullNameEDt.setEnabled(editable);

        // Additional logic or UI changes based on the state of "Edit" button
        if (editable) {
            btnSave.setEnabled(true);
            btnChooseImage.setEnabled(true);
            FullNameEDt.setEnabled(true);
            // Perform actions when "Edit" button is enabled (for the first time)
        } else {
            btnSave.setEnabled(false);
            btnChooseImage.setEnabled(false);
            FullNameEDt.setEnabled(false);

            // Perform actions when "Edit" button is disabled (after the first click)
        }
    }
    // The method to open the gallery
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
        // Handle the selected image URI, e.g., set it to an ImageView
        profileImage.setImageURI(selectedImageUri);
    }
    private void btnSave(){
        Drawable drawable = profileImage.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            byte[] image = ImageHelper.bitmapToByteArray(bitmap);
            String fullName = FullNameEDt.getText().toString();
            boolean result = helper.updateUserData(userID,fullName,image);
            if(result){
                //Retrieve image and fullname;
                UserModel updateUser = helper.getUserDataById(userID);
                FullNameEDt.setText(updateUser.getFullName());
                byte[] binaryPhoto = updateUser.getPhoto();
                Bitmap updatedPhoto = ImageHelper.byteArrayToBitmap(binaryPhoto);
                profileImage.setImageBitmap(updatedPhoto);
                DisableButtons();

                Toast.makeText(requireContext(), "Successfully saved.", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(requireContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
            // Now you can do something with the bitmap, like save it to storage or upload it.
            // Example: saveBitmapToFile(bitmap);
            // Insert into database;
        } else {
            // Handle the case where the drawable is not a BitmapDrawable
            Toast.makeText(requireContext(), "Failed to save.", Toast.LENGTH_SHORT).show();
        }


    }
    private void DisableButtons(){
        FullNameEDt.setEnabled(false);
        btnSave.setEnabled(false);
        btnChooseImage.setEnabled(false);
        btnEdit.setEnabled(true);
    }
    private void btnLogout()
    {
        Intent i = new Intent(requireContext(), LoginView.class);
        startActivity(i);
    }


}