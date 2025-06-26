# blizART

**blizART** is a personal productivity and art management Android application developed using Jetpack Compose. It helps artists and creative users organize their projects, track progress, and manage notes and checklists in an intuitive, visually appealing interface.

## Features

- **Dashboard:** Overview of current art projects, categories, and progress.
- **Project Tracking:** Add and monitor "work in progress" items.
- **Notes:** Create and manage notes for each project.
- **Checklist:** Keep track of tasks and to-dos within your creative workflow.
- **Authentication:** Secure login and signup, integrated with Firebase.
- **Profile Management:** View and update profile details.
- **Material Design:** Modern UI using Jetpack Compose, theming, and responsive layouts.

## Technologies Used

- **Kotlin**: Main programming language.
- **Jetpack Compose**: UI framework for declarative Android interfaces.
- **Firebase Auth & Firestore**: User authentication and real-time data storage.
- **Coroutines & Flow**: For asynchronous operations and real-time updates.
- **MVVM Architecture**: Clean separation of UI, logic, and data.

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Ars3nicc/blizART.git
   cd blizART
   ```

2. **Open in Android Studio**  
   Open the project folder in [Android Studio](https://developer.android.com/studio).

3. **Configure Firebase:**  
   - Create a Firebase project at [console.firebase.google.com](https://console.firebase.google.com/)
   - Download `google-services.json` and place it in `app/`.

4. **Run the app:**  
   Connect your Android device or use an emulator, then click "Run".

## Usage

- **Sign Up / Log In** to access your dashboard.
- Use the bottom navigation to switch between Dashboard, Projects, Notes, and Profile.
- Add new projects, notes, or checklist items as needed.
- Your data is securely stored with Firebase.

## Contributing

Contributions, issues, and feature requests are welcome!  
Feel free to open a pull request.

## License

This project is licensed under the MIT License.

---

### Overview

blizART is designed for artists and creators who want to manage their workflow digitally. It supports adding, tracking, and organizing artwork projects, maintaining work notes, and checking off task lists. The app leverages Google's Firebase for robust authentication and real-time cloud storage, ensuring that your notes and progress are accessible and secure.

The interface is built on Jetpack Compose, offering a modern, smooth, and customizable user experience. With clear separation of business logic and UI (MVVM pattern), the codebase is structured for scalability and easy maintenance.

**Main Modules:**
- **Main Dashboard:** Central hub for navigation and project overviews.
- **Work Notes & Progress:** Grid and list views for notes and ongoing tasks.
- **Authentication:** Handles login, registration, and session management.
- **Repository Layer:** Interfaces with Firebase for all data storage and retrieval.

Whether you are an independent artist or simply want to keep your creative projects organized, blizART provides a focused, distraction-free environment to help you get things done.
