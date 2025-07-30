# 📱 Restaurant Menu App

A modern Android application that displays a restaurant's menu retrieved from a remote JSON endpoint. Users can sort, filter, and favorite items with full offline support and real-time connectivity awareness.

---
## 📸 Screenshots

 ![Home](/res/image1.png)

---

## 📦 Download

You can download the latest APK from the [download](https://github.com/alihrhera/HilalPlayTest/blob/master/res/).


## 🧩 Features Implemented

### ✅ Core Functionality
- ✅ Fetch menu data from remote JSON API using Retrofit.
- ✅ Store data locally using **Room** for offline access.
- ✅ Full **MVVM architecture** with **Clean Architecture** layering.
- ✅ Use of modern **Jetpack libraries**: ViewModel, Compose, Coroutines, Lifecycle.
- ✅ Display:
  - Categories: *hamburgers*, *pasta*, *drinks*, *sandwiches*.
  - Item details: image, name, description, price, availability, rating, tags (vegan/hot).
- ✅ Sorting and Filtering by:
  - Price (asc/desc)
  - Rating
  - Vegan / Non-Vegan
  - Hot / Not Hot
  - Availability

### 📶 Connectivity
- ✅ Live network connectivity detection using `ConnectivityManager` with `NetworkCallback`.
- ✅ Offline-first support with graceful fallback and error messages.

### 💾 Local Preferences
- ✅ Allow users to **favorite** items (locally stored).
- ✅ **Pull-to-refresh** functionality with visual feedback.

### 💡 UI/UX
- ✅ Built entirely using **Jetpack Compose**.
- ✅ Responsive and polished interface with:
  - Category tabs
  - Scrollable chips for filters
  - Dynamic icons for availability, rating, favorites, etc.

---

## 🔧 Architecture & Patterns

- 🧱 **Clean Architecture**:
  - `Data`, `Domain`, `Ui` layers
- 🧠 Design Patterns used:
  - **Singleton** for data access & DI
  - **Strategy Pattern** for sorting logic
- 🔁 **State management** with Compose and Flow/StateFlows

---

## 🛠 Tech Stack

| Layer          | Library / Tool                      |
|----------------|-------------------------------------|
| UI             | Jetpack Compose                     |
| DI             | Hilt                                |
| Networking     | Retrofit + OkHttp                   |
| Local Storage  | Room                                |
| Coroutines     | Kotlin Coroutines                   |
| JSON Parsing   | GSON                                |
| Build System   | Gradle 8.1.0                        |
| IDE            | Android Studio Meerkat (2024.3.2)   |
| Architecture   | MVVM + Clean Architecture           |

---

## 🚀 Bonus Features (Implemented)

- ❤️ Favorite items saved locally
- 🔄 Pull-to-refresh with Compose
- 📦 Fully built with **Jetpack Compose** (no XML)

---

## 🔍 API Used

```http
GET https://api.jsonbin.io/v3/b/6882874df7e7a370d1ed6cc1/latest
Headers:
X-Master-Key:API_KEY
