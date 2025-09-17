# WeatherApp – API Report

This application integrates with the **OpenWeather API** (https://openweathermap.org/) to fetch real-time weather data. The API provides structured JSON responses containing valuable information such as temperature, weather conditions, humidity, wind speed, and weather icons.

We use two main endpoints from the API:
- **`/weather?q={city}`** – fetches the current weather for a given city name.  
- **`/weather?lat={lat}&lon={lon}`** – fetches the current weather based on geographic coordinates.  

The app communicates with the API using **Retrofit**, a popular HTTP client for Kotlin and Android. When the user inputs either a city name or latitude/longitude values, the app sends an asynchronous request to the API. Retrofit handles the parsing of the JSON response into Kotlin **data classes**.

The data is then exposed to the UI through a **ViewModel** using **StateFlow**. The ViewModel ensures that network operations happen on background threads, while the UI remains responsive. Depending on the result, the app updates the UI with either weather information or an error message.

For example, a request such as `/weather?q=Stockholm&appid=API_KEY` returns JSON with fields like `main.temp`, `weather[0].description`, and `weather[0].icon`. This data is displayed in the app: temperature in Celsius, textual weather description, and an icon (fetched from OpenWeather’s image resources).

This integration ensures the application provides **real-time, valuable, and user-friendly weather information** to the end user.
