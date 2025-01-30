package com.example.eventadmin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private LinearLayout addEventButton, editEventButton, deleteEventButton, viewEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the buttons
        addEventButton = findViewById(R.id.addEventButton);
        editEventButton = findViewById(R.id.editEventButton);
        deleteEventButton = findViewById(R.id.deleteEventButton);
        viewEventButton = findViewById(R.id.viewEventButton);

        // Set up click listeners for each button
        addEventButton.setOnClickListener(v -> openAddEventActivity());
        editEventButton.setOnClickListener(v -> openEditEventActivity());
        deleteEventButton.setOnClickListener(v -> openDeleteEventActivity());
        viewEventButton.setOnClickListener(v -> openViewEventActivity());
    }

    // Open Add Event screen
    private void openAddEventActivity() {
        Intent intent = new Intent(MainActivity.this, AddEventActivity.class);
        startActivity(intent);
    }

    // Open Edit Event screen
    private void openEditEventActivity() {
        Intent intent = new Intent(MainActivity.this, EditEventActivity.class);
        startActivity(intent);
    }

    // Open Delete Event screen
    private void openDeleteEventActivity() {
        Intent intent = new Intent(MainActivity.this, DeleteEventActivity.class);
        startActivity(intent);
    }

    // Open View Event screen
    private void openViewEventActivity() {
        Intent intent = new Intent(MainActivity.this, ViewEventActivity.class);
        startActivity(intent);
    }
}