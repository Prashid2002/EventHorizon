# EventHorizon - Local Event Finder App

## Overview
**EventHorizon** is a local event discovery app designed to help users find, explore, and book events seamlessly. The app is structured into two modules: **Admin** and **User**.

## Features
### User Module
- **Browse Events**: Discover upcoming events in your locality.
- **Book Tickets**: Reserve your spot for events with QR-based entry validation.
- **Search Events**: Use keywords to find events of interest.

### Admin Module
- **Create Events** ✅ (Currently Functional)
- **Edit Events** 🔜 (Upcoming Feature)
- **Delete Events** 🔜 (Upcoming Feature)
- **View Event Details** 🔜 (Upcoming Feature)

## Technologies Used
- **Frontend**: Java, XML (Android Studio)
- **Backend**: Firebase Realtime Database
- **QR Code Handling**: ZXing Library

## Installation
1. Clone this repository:
   ```bash
   git clone https://github.com/Prashid2002/EventHorizon.git
   ```
2. Open the project in **Android Studio**.
3. Sync Gradle and ensure dependencies are installed.
4. Run the app on an **emulator** or **physical device**.

## Dependencies
Add the following to your `build.gradle` file:
```gradle
dependencies {
    implementation 'com.google.firebase:firebase-database:20.0.4'
    implementation 'com.google.zxing:core:3.3.0'
}
```

## Usage
### For Users
1. Open the app and browse available events.
2. Click on an event to view details.
3. Book a ticket and receive a QR code for entry.

### For Admins
1. Log in to the Admin panel.
2. Use the **Create Event** feature to add new events.
3. Upcoming features will include Edit, Delete, and View options.

## Screenshots
(Include relevant screenshots here)

## Contributing
We welcome contributions! Please follow these steps:
1. Fork the repository.
2. Create a new branch: `git checkout -b feature-branch`.
3. Make your changes and commit: `git commit -am 'Add feature'`.
4. Push to the branch: `git push origin feature-branch`.
5. Create a pull request.

## License
This project is open-source and available under the MIT License.

