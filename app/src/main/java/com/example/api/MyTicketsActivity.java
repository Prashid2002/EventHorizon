package com.example.api;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class MyTicketsActivity extends AppCompatActivity {

    private RecyclerView ticketsRecyclerView;
    private TicketAdapter ticketAdapter;
    private List<BookingModel> ticketList;
    private FirebaseFirestore firestore;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets);

        ticketsRecyclerView = findViewById(R.id.ticketsRecyclerView);
        ticketsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ticketList = new ArrayList<>();
        ticketAdapter = new TicketAdapter(this, ticketList);
        ticketsRecyclerView.setAdapter(ticketAdapter);

        firestore = FirebaseFirestore.getInstance();
        fetchTickets();

        int selectedItem = getIntent().getIntExtra("selected_item", R.id.navigation_home);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(selectedItem);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.navigation_home) {
                startActivity(new Intent(MyTicketsActivity.this, MainActivity.class)
                        .putExtra("selected_item", R.id.navigation_home));
                finish();  // Close the current activity
                return true;
            } else if (id == R.id.navigation_tickets) {
                // Already in Tickets activity
                return true;
            } else if (id == R.id.navigation_favorites) {
                Toast.makeText(MyTicketsActivity.this, "Favorites feature coming soon!", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.navigation_profile) {
                Toast.makeText(MyTicketsActivity.this, "Profile feature coming soon!", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }

    private void fetchTickets() {
        firestore.collection("bookings")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ticketList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            BookingModel booking = document.toObject(BookingModel.class);
                            ticketList.add(booking);
                        }
                        ticketAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Failed to fetch tickets!", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
