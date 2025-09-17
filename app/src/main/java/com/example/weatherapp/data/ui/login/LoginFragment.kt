package com.example.weatherapp.data.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val PREFS = "user_prefs"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        val prefs = requireContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val saved = prefs.getBoolean("loggedIn", false)

        if (saved) {
            findNavController().navigate(R.id.action_login_to_home)
        }

        binding.btnLogin.setOnClickListener {
            val user = binding.etUser.text.toString().trim()
            val pass = binding.etPass.text.toString().trim()

            if (user.isNotEmpty() && pass.isNotEmpty()) {
                prefs.edit().putBoolean("loggedIn", true).apply()
                prefs.edit().putString("username", user).apply()
                findNavController().navigate(R.id.action_login_to_home)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Ange användarnamn och lösenord",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
