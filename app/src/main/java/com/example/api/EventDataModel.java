package com.example.api;
public class EventDataModel {
    private String title;
    private String venue;
    private String timeAndDate;
    private String price;
    private String imageUrl;

    // Default constructor for Firebase
    public EventDataModel() {}

    public EventDataModel(String title, String venue, String timeAndDate, String price, String imageUrl) {
        this.title = title;
        this.venue = venue;
        this.timeAndDate = timeAndDate;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(String timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
