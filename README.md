# 📝 Notes App

A modern Android Notes Application built with **Jetpack Compose**, **MVI Architecture**, **Room Database**, **Retrofit**, **Dagger Hilt**, and **Pixabay API**.

The application allows users to create, edit, delete, search, and organize notes while attaching beautiful images fetched from Pixabay.

---

## ✨ Features

### 📝 Note Management

* Create new notes
* Edit existing notes
* Delete notes
* Save note title and description
* Persistent local storage using Room Database

### 🖼️ Image Search

* Search images from Pixabay API
* Attach images to notes
* Display note images in the note list and detail screen

### 🔍 Sorting

* Sort notes by:

  * Title (A-Z)
  * Creation Date (Newest First)

### 🎨 Modern UI

* Built entirely with Jetpack Compose
* Material Design UI
* Responsive and clean user experience
* Splash Scrren by lotties

### 🏗️ Architecture

* MVI (Model-View-Intent) Pattern
* Unidirectional Data Flow
* Separation of Concerns
* Scalable and Maintainable Codebase

### 🔧 Dependency Injection

* Dagger Hilt

### 🌐 Networking

* Retrofit
* Kotlin Coroutines
* Pixabay REST API

### 💾 Local Database

* Room Database
* DAO Pattern
* Offline Data Persistence

### 🧪 Testing

* Unit Testing with JUnit4
* UI Testing with Compose Testing Framework
* Android Instrumentation Tests

---

## 🛠️ Tech Stack

| Category             | Technology        |
| -------------------- | ----------------- |
| Language             | Kotlin            |
| UI                   | Jetpack Compose   |
| Architecture         | MVI               |
| Dependency Injection | Dagger Hilt       |
| Networking           | Retrofit          |
| Database             | Room              |
| Asynchronous         | Kotlin Coroutines |
| Image API            | Pixabay API       |
| Testing              | JUnit4            |
| UI Testing           | Compose UI Test   |
| Build Tool           | Gradle            |

---

## 📱 Screenshots

Add screenshots here:

|Splash Screen| Home Screen | Add Note   | Image Search |
| ----------- | ---------- | ------------ | ------------ |
| <img width="1280" height="2856" alt="Screenshot_20260529_114307" src="https://github.com/user-attachments/assets/02c3adcb-1ab8-41b5-9495-b47afddb2b12" /> | <img width="1280" height="2856" alt="Screenshot_20260529_114210" src="https://github.com/user-attachments/assets/7e6b588c-a218-416e-a1f6-035d6d5df640" />
| <img width="1280" height="2856" alt="Screenshot_20260529_114532" src="https://github.com/user-attachments/assets/a0c9401e-586e-4a93-a0aa-dfac2eb76e82" /> | <img width="1280" height="2856" alt="Screenshot_20260529_114247" src="https://github.com/user-attachments/assets/8dffd764-3296-4764-b0d6-e0acf707c7b4" />
|

---

## 📂 Project Structure

```text
com.example.notesapp
│
├── data
│   ├── local
│   ├── remote
│   └── repository
│
├── domain
│   ├── model
│   └── usecase
│
├── presentation
│   ├── notes
│   ├── add_edit_note
│   └── image_search
│
├── di
│
└── test
```

---

## 🚀 Getting Started

### Clone Repository

```bash
git clone https://github.com/your-username/notes-app.git
```

### Pixabay API Key

Create a free account on Pixabay and generate an API key.

Add your key to:

```properties
API_KEY=YOUR_PIXABAY_API_KEY
```

### Build & Run

```bash
./gradlew assembleDebug
```

or simply run the project from Android Studio.

---

## 📋 Future Improvements

* Dark Mode
* Search Notes
* Pagination for Pixabay Images
* Export Notes to PDF
* Cloud Synchronization
* Multi-language Support

---

## 👨‍💻 Author

Developed by Bank

This project was created to practice modern Android development using Jetpack Compose and Clean Architecture principles.
