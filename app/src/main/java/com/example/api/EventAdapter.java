package com.example.api;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context context;
    private List<EventDataModel> eventList;

    public EventAdapter(Context context, List<EventDataModel> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        ImageView eventImage;
        TextView eventTitle, eventVenue, eventTimeDate, eventPrice;
        Button bookNowButton;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            eventImage = itemView.findViewById(R.id.card_image);
            eventTitle = itemView.findViewById(R.id.card_title);
            eventVenue = itemView.findViewById(R.id.card_venue);
            eventTimeDate = itemView.findViewById(R.id.card_time_date);
            eventPrice = itemView.findViewById(R.id.card_price);
            bookNowButton = itemView.findViewById(R.id.book_now_button);
        }
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_card, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventDataModel event = eventList.get(position);

        // Load image using Glide (better than AsyncTask)
        Glide.with(context).load(event.getImageUrl()).into(holder.eventImage);

        holder.eventTitle.setText(event.getTitle());
        holder.eventVenue.setText(event.getVenue());
        holder.eventTimeDate.setText(event.getTimeAndDate());
        holder.eventPrice.setText(event.getPrice());

        holder.bookNowButton.setOnClickListener(v -> showBookingDialog(event));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    private void showBookingDialog(EventDataModel event) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_booking, null);
        builder.setView(view);

        EditText etFullName = view.findViewById(R.id.et_full_name);
        EditText etEmail = view.findViewById(R.id.et_email);
        RadioGroup rgGender = view.findViewById(R.id.rg_gender);
        EditText etDob = view.findViewById(R.id.et_dob);
        EditText etContact = view.findViewById(R.id.et_contact);
        Button btnBookNow = view.findViewById(R.id.btn_book_now);

        AlertDialog dialog = builder.create();
        dialog.show();

        btnBookNow.setOnClickListener(v -> {
            String fullName = etFullName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String dob = etDob.getText().toString().trim();
            String contact = etContact.getText().toString().trim();
            int selectedGenderId = rgGender.getCheckedRadioButtonId();
            String gender = (selectedGenderId == R.id.rb_male) ? "Male" : "Female";

            if (fullName.isEmpty() || email.isEmpty() || dob.isEmpty() || contact.isEmpty()) {
                Toast.makeText(context, "All fields are required!", Toast.LENGTH_SHORT).show();
                return;
            }

            saveBookingToFirestore(event, fullName, email, gender, dob, contact);
            dialog.dismiss();
        });
    }

    private void saveBookingToFirestore(EventDataModel event, String fullName, String email, String gender, String dob, String contact) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> booking = new HashMap<>();
        booking.put("eventTitle", event.getTitle());
        booking.put("eventVenue", event.getVenue());
        booking.put("eventTimeAndDate", event.getTimeAndDate());
        booking.put("eventPrice", event.getPrice());
        booking.put("fullName", fullName);
        booking.put("email", email);
        booking.put("gender", gender);
        booking.put("dob", dob);
        booking.put("contact", contact);

        db.collection("bookings")
                .add(booking)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(context, "Booking Successful!", Toast.LENGTH_SHORT).show();
                    Log.d("Firestore", "Booking saved with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Booking Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Firestore", "Booking failed", e);
                });
    }
}
