package com.example.weatherapp.data.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.databinding.FragmentDetailsBinding
import com.example.weatherapp.data.WeatherRepository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val repo = WeatherRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val args = arguments
        lifecycleScope.launch {
            try {
                val response = when {
                    args?.containsKey("city") == true -> {
                        val city = args.getString("city") ?: ""
                        repo.getByCity(city)
                    }
                    args?.containsKey("lat") == true && args.containsKey("lon") -> {
                        val lat = args.getString("lat")!!.toDouble()
                        val lon = args.getString("lon")!!.toDouble()
                        repo.getByCoords(lat, lon)
                    }
                    else -> {
                        binding.textDetails.text = "Ingen plats angiven"
                        return@launch
                    }
                }

                // Visa resultat
                val name = response.name
                val temp = response.main.temp
                val desc = response.weather.firstOrNull()?.description ?: ""
                val icon = response.weather.firstOrNull()?.icon ?: "01d"

                binding.textDetails.text = "$name\n${temp}°C\n$desc"

                val iconUrl = "https://openweathermap.org/img/wn/${icon}@2x.png"
                Picasso.get().load(iconUrl).into(binding.imageWeather)

                // Enkel notis-logic: om text innehåller rain -> skapa notis (valfritt)
                if (desc.lowercase().contains("rain")) {
                    showSimpleNotification("Regnvarning", "Det ser ut att regna i $name")
                }

            } catch (e: Exception) {
                binding.textDetails.text = "Fel: ${e.message}"
            }
        }

        return binding.root
    }

    private fun showSimpleNotification(title: String, text: String) {
        // Minimal; för att hålla koden kort använder vi Context.notificationManager i senare iteration
        // Du kan implementera NotificationCompat här om du vill. (Jag höll det kort pga plats)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
