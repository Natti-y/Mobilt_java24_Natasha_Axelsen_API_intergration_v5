# Mobilt_java24_DittNamn_API_intergration_v5

## Description
In this project I have created a weather application where users can log in, search for a city or coordinates, and then see the current weather. The app integrates with the **OpenWeather REST API** to fetch real-time weather data. The user can choose whether to search by city name or latitude/longitude through the settings screen.

The project follows a multi-fragment structure with proper backstack navigation. The UI adapts to both **portrait and landscape** orientation. All code is written in **Kotlin**.

---

## Functionality
- Login screen with shared preferences to save session  
- Search screen where the user enters city name or coordinates  
- Weather details fetched from OpenWeather API  
- Displays temperature, description, humidity, wind speed and icon  
- Switch in settings to choose between “City” or “Coordinates” mode  
- Logout clears preferences and returns to login screen  

---

## API Integration
I used the **OpenWeather API** (https://openweathermap.org/).  
Endpoints:  
- `/weather?q={city}&appid={API_KEY}` – fetch weather by city name  
- `/weather?lat={lat}&lon={lon}&appid={API_KEY}` – fetch weather by coordinates  

The responses are parsed with **Retrofit** into Kotlin data classes. The data is exposed through a **ViewModel** using StateFlow and displayed in the UI with temperature in Celsius, weather description, and a weather icon.

---

## Setup/Installation Requirements
1. Clone the repo  
2. Open in Android Studio  
3. Add your OpenWeather `API_KEY` in `ApiClient.kt`  
4. Build & run on an emulator or device  

---

## Project Features
- 4 fragments: Login, Home, Search, Details (+ Settings)  
- Backstack navigation implemented with Navigation Component  
- API data fetched with Retrofit and displayed in UI  
- Orientation support (portrait & landscape)  
- Kotlin only implementation  
