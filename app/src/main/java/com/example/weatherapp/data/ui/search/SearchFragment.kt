package com.example.weatherapp.data.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.data.ui.settings.SettingsFragment
import com.example.weatherapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        // Anpassa hint efter switchen
        updateHint()

        binding.btnShowDetails.setOnClickListener {
            val input = binding.inputSearch.text.toString().trim()
            if (input.isEmpty()) {
                Toast.makeText(requireContext(), "Ange stad eller koordinater", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val bundle = Bundle()
            if (SettingsFragment.useCoordinates) {
                // Koordinater
                val parts = input.split(",").map { it.trim() }
                if (parts.size != 2) {
                    Toast.makeText(requireContext(), "Ange lat,lon", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val lat = parts[0].toDoubleOrNull()
                val lon = parts[1].toDoubleOrNull()
                if (lat == null || lon == null) {
                    Toast.makeText(requireContext(), "Ogiltiga koordinater", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                bundle.putDouble("lat", lat)
                bundle.putDouble("lon", lon)
            } else {
                // Stadnamn
                bundle.putString("city", input)
            }

            findNavController().navigate(
                R.id.action_search_to_details,
                bundle
            )
        }

        return binding.root
    }

    private fun updateHint() {
        if (SettingsFragment.useCoordinates) {
            binding.inputSearch.hint = "Ange latitud, longitud (t.ex. 59.33,18.06)"
        } else {
            binding.inputSearch.hint = "Ange stad (t.ex. Stockholm)"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
