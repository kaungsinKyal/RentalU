package com.example.restart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView tvHeader;
    private Handler handler = new Handler();
    private int progressStatus = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        tvHeader = findViewById(R.id.tvHeader);

        // Start updating the progress bar
        startProgressBarUpdate();

        // Set onTouchListener to the root layout


    }

    private void startProgressBarUpdate() {
        new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus += 1;

                // Update the progress bar on the UI thread
                handler.post(() -> progressBar.setProgress(progressStatus));

                try {
                    // Sleep for a short duration to simulate progress
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // When progress reaches 100%, update the text
            handler.post(() -> {
                tvHeader.setText("Touch the screen to continue...");
                // You can make the button visible or perform other actions here
                View rootView = findViewById(android.R.id.content);
                rootView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        // Handle touch event, for example, navigating to the next screen
                        // This is just an example, you should replace it with your actual logic
                        navigateToNextScreen();
                        return true;
                    }
                });
            });
        }).start();
    }

    private void navigateToNextScreen() {
        // Add your logic to navigate to the next screen
        // For example:
        // Intent intent = new Intent(MainActivity.this, NextActivity.class);
        // startActivity(intent);
        Intent i = new Intent(MainActivity.this, LoginView.class);
        startActivity(i);
    }
}
