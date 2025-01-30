package com.example.api;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView eventRecyclerView;
    EventAdapter eventAdapter;
    FirebaseDatabase database;
    DatabaseReference eventRef;
    List<EventDataModel> eventList;
    BottomNavigationView bottomNavigationView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase
        database = FirebaseDatabase.getInstance();
        eventRef = database.getReference("events");

        // Set up RecyclerView
        eventRecyclerView = findViewById(R.id.EventRecyclerView);
        eventRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize event list
        eventList = new ArrayList<>();

        // Set up adapter
        eventAdapter = new EventAdapter(this, eventList);

        eventRecyclerView.setAdapter(eventAdapter);

        progressBar = findViewById(R.id.progressBar);

        // Fetch data from Firebase
        fetchEventDataFromFirebase();
        int selectedItem = getIntent().getIntExtra("selected_item", R.id.navigation_home);


        // Initialize Bottom Navigation View
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(selectedItem);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.navigation_home) {
                startActivity(new Intent(MainActivity.this, MainActivity.class)
                        .putExtra("selected_item", R.id.navigation_home));
                return true;
            } else if (id == R.id.navigation_tickets) {
                startActivity(new Intent(MainActivity.this, MyTicketsActivity.class)
                        .putExtra("selected_item", R.id.navigation_tickets));
                return true;
            } else if (id == R.id.navigation_favorites) {
                Toast.makeText(MainActivity.this, "Favorites feature coming soon!", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.navigation_profile) {
                Toast.makeText(MainActivity.this, "Profile feature coming soon!", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }

    private void fetchEventDataFromFirebase() {

        progressBar.setVisibility(ProgressBar.VISIBLE);

        eventRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    EventDataModel event = snapshot.getValue(EventDataModel.class);
                    if (event != null) {
                        eventList.add(event);
                    }
                }
                eventAdapter.notifyDataSetChanged();

                progressBar.setVisibility(ProgressBar.GONE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(ProgressBar.GONE);

            }
        });
    }

    // Handle Bottom Navigation Clicks
    private boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navigation_home) {
            return true; // Already on Home
        } else if (id == R.id.navigation_tickets) {
            startActivity(new Intent(this, MyTicketsActivity.class));
            return true;
        } else if (id == R.id.navigation_favorites) {
            Toast.makeText(this, "Favorites feature coming soon!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.navigation_profile) {
            Toast.makeText(this, "Profile feature coming soon!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
