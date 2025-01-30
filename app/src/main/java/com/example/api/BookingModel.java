package com.example.api;

public class BookingModel {
    private String eventTitle, eventVenue, eventTimeAndDate, eventPrice, fullName, email;

    public BookingModel() {
        // Required empty constructor for Firestore
    }

    public BookingModel(String eventTitle, String eventVenue, String eventTimeAndDate, String eventPrice, String fullName, String email) {
        this.eventTitle = eventTitle;
        this.eventVenue = eventVenue;
        this.eventTimeAndDate = eventTimeAndDate;
        this.eventPrice = eventPrice;
        this.fullName = fullName;
        this.email = email;
    }

    public String getEventTitle() { return eventTitle; }
    public String getEventVenue() { return eventVenue; }
    public String getEventTimeAndDate() { return eventTimeAndDate; }
    public String getEventPrice() { return eventPrice; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
}
