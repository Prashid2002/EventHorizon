package com.example.api;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView splashText = findViewById(R.id.splash_text);

        // Load the fade-in animation
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Start the animation on the TextView
        splashText.startAnimation(fadeIn);
        // Set up a Handler to delay the transition to the next screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the Main Activity after 3 seconds
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);

                // Close the splash screen so it doesn't stay in the back stack
                finish();
            }
        }, 2000); // 3000 milliseconds = 3 seconds
    }
}
