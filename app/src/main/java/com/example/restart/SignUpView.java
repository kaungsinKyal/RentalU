package com.example.restart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.BitSet;

public class SignUpView extends AppCompatActivity {
    Button btnSignUp, btnGotoLogin;
    DbHelper helper;
    ValidationHelpr validationHelpr;
    EditText fullNameView,UsernameView,PasswordView,ConPasswordView,PhoneView;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_view);
        logo = findViewById(R.id.logo);
        btnSignUp = findViewById(R.id.btnSignUp);
        fullNameView = findViewById(R.id.inputFullName);
        UsernameView = findViewById(R.id.inputUsername);
        PasswordView = findViewById(R.id.inputPassword);
        ConPasswordView =findViewById(R.id.inputConPassword);
        PhoneView = findViewById(R.id.inputPhone);
        btnGotoLogin = findViewById(R.id.btnGoToLogin);
        btnGotoLogin.setOnClickListener(v -> GoTologin());

        helper = new DbHelper(SignUpView.this);
        validationHelpr = new ValidationHelpr(SignUpView.this);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = fullNameView.getText().toString();
                String username = UsernameView.getText().toString();
                String password = PasswordView.getText().toString();
                String conPassword = ConPasswordView.getText().toString();
                String phone = PhoneView.getText().toString();

                boolean isValidFullName = validationHelpr.validateFullName(fullName);
                boolean isValidUsername = validationHelpr.validateUsername(username);
                boolean isValidPassword =validationHelpr.validatePassword(password);
                boolean isValidConPassword =validationHelpr.validatePassword(conPassword);
                boolean isValidPhone = validationHelpr.validatePhone(phone);

                boolean allFine = validationHelpr.cHeckAll(isValidFullName,isValidUsername,isValidPassword,isValidConPassword,isValidPhone);
                if(allFine){
                    boolean CheckUserExisted = helper.isUserExists(username,password);
                    if(!CheckUserExisted)
                    {
                        boolean isPHoneExisted = helper.isPhoneExists(phone);
                        if(!isPHoneExisted)
                        {
                            // Insert into database;
                            UserModel newUser = new UserModel(username,password,conPassword,fullName,phone);
                            helper.addUser(newUser);
                            Toast.makeText(SignUpView.this, "Sign Up Successfully.", Toast.LENGTH_SHORT).show();
                            ClearTextBox();
                        }else{
                            Toast.makeText(SignUpView.this, "Account has already been created with this phone number.", Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        Toast.makeText(SignUpView.this, "Username name is already existed.", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(SignUpView.this, "Check the input fields.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private void GoTologin()
    {
        // Implements  later;
        Intent i = new Intent(SignUpView.this, LoginView.class);
        startActivity(i);
    }
    private void ClearTextBox()
    {
        fullNameView.setText("");
        UsernameView.setText("");
        PasswordView.setText("");
        ConPasswordView.setText("");
        PhoneView.setText("");
    }

}