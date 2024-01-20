package com.example.restart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginView extends AppCompatActivity {
    Button loginBtn , signupBtn;
    EditText edtUsername,edtPassword;

    DbHelper helper;
    ValidationHelpr validationHelpr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
        loginBtn = findViewById(R.id.btnLogin);
        signupBtn = findViewById(R.id.btnGotoSignUp);
        edtUsername = findViewById(R.id.inputUsername);
        edtPassword = findViewById(R.id.inputPassword);
        helper = new DbHelper(this);
        validationHelpr = new ValidationHelpr(this);
        signupBtn.setOnClickListener(v -> navigateToSignUpView());
        loginBtn.setOnClickListener(v -> loginProcess());

    }
    private void navigateToSignUpView()
    {
        Intent i = new Intent(LoginView.this,SignUpView.class);
        startActivity(i);
    }
    private void loginProcess()
    {
        if(edtUsername.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Username is empty.", Toast.LENGTH_SHORT).show();
        }
        if(edtPassword.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Password is empty.", Toast.LENGTH_SHORT).show();
        }
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        boolean isValid = helper.isValidCredentials(username,password);

        if(isValid)
        {
            Toast.makeText(this, "Access Granted.", Toast.LENGTH_SHORT).show();

            // retrieve userID and pass to another page.
           int userID = helper.getUserId(username,password);

           Intent i = new Intent(LoginView.this, HomePageView.class);
           i.putExtra("userID",userID);
           startActivity(i);


        }else
        {
            Toast.makeText(this, "Account does not exist or please check your credentials.", Toast.LENGTH_SHORT).show();
        }
    }
}