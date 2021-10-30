package com.example.task5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task5.databinding.SecondFragmentBinding

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        val binding = SecondFragmentBinding.inflate(layoutInflater)
        val navController = findNavController()

        binding.buttonSecondFirst.setOnClickListener {
            navController.navigate(R.id.action_secondFragment_to_firstFragment)
        }

        binding.buttonSecondThird.setOnClickListener {
            navController.navigate(R.id.action_secondFragment_to_thirdFragment)
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