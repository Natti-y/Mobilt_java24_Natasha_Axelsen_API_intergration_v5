package com.example.weatherapp.data.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Navigera till sök
        binding.btnGoToSearch.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }

        // Navigera till inställningar
        binding.btnGoToSettings.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
        }

        // Logga ut och rensa backstacken
        binding.btnLogout.setOnClickListener {
            // Rensa login-flagga
            requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                .edit().putBoolean("loggedIn", false).apply()

            // Rensa navigationstacken och gå till login
            val options = NavOptions.Builder()
                .setPopUpTo(R.id.nav_graph, true) // töm backstack
                .build()
            findNavController().navigate(R.id.loginFragment, null, options)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
