package com.example.task5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task5.databinding.FirstFragmentBinding

class FirstFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        val binding = FirstFragmentBinding.inflate(layoutInflater)
        val navController = findNavController()

        binding.buttonFirstSecond.setOnClickListener {
            navController.navigate(R.id.action_firstFragment_to_secondFragment)
        }

        binding.navView.setNavigationItemSelectedListener{ moveToAbout(it) }

        return binding.root
    }

    private fun moveToAbout(item: MenuItem) : Boolean {

        return if (item.itemId == R.id.nav_message) {
            findNavController().navigate(R.id.global_about)
            true
        } else {
            false
        }
    }
}