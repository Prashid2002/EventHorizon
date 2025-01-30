package com.example.eventadmin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEventActivity extends AppCompatActivity {

    private EditText titleEditText, venueEditText, timeEditText, priceEditText;
    private Button saveButton;

    private FirebaseDatabase database;
    private DatabaseReference eventsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        // Initialize Firebase
        database = FirebaseDatabase.getInstance();
        eventsRef = database.getReference("events");

        // Initialize UI components
        titleEditText = findViewById(R.id.titleEditText);
        venueEditText = findViewById(R.id.venueEditText);
        timeEditText = findViewById(R.id.timeEditText);
        priceEditText = findViewById(R.id.priceEditText);
        saveButton = findViewById(R.id.saveButton);

        // Set OnClickListener for the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEvent();
            }
        });
    }

    private void saveEvent() {
        // Get input values
        String title = titleEditText.getText().toString().trim();
        String venue = venueEditText.getText().toString().trim();
        String time = timeEditText.getText().toString().trim();
        String price = priceEditText.getText().toString().trim();

        // Validate input fields
        if (TextUtils.isEmpty(title)) {
            titleEditText.setError("Event Title is required");
            return;
        }
        if (TextUtils.isEmpty(venue)) {
            venueEditText.setError("Event Venue is required");
            return;
        }
        if (TextUtils.isEmpty(time)) {
            timeEditText.setError("Event Time & Date is required");
            return;
        }
        if (TextUtils.isEmpty(price)) {
            priceEditText.setError("Event Price is required");
            return;
        }

        // Create EventDataModel object
        EventAdminModel event = new EventAdminModel();
        event.setTitle(title);
        event.setVenue(venue);
        event.setTimeAndDate(time);
        event.setPrice(price);

        // Push event data to Firebase
        String eventId = eventsRef.push().getKey(); // Generate unique key
        if (eventId != null) {
            eventsRef.child(eventId).setValue(event).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Notify success and clear the input fields
                    Toast.makeText(AddEventActivity.this, "Event added successfully", Toast.LENGTH_SHORT).show();
                    clearInputs();
                } else {
                    // Notify failure
                    Toast.makeText(AddEventActivity.this, "Failed to add event. Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void clearInputs() {
        // Clear all input fields after saving the event
        titleEditText.setText("");
        venueEditText.setText("");
        timeEditText.setText("");
        priceEditText.setText("");
    }
}
