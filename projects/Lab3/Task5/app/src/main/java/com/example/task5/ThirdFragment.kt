package com.example.task5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task5.databinding.ThirdFragmentBinding

class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        val binding = ThirdFragmentBinding.inflate(layoutInflater)
        val navController = findNavController()

        binding.buttonThirdFirst.setOnClickListener {
            navController.navigate(R.id.action_thirdFragment_to_firstFragment)
        }

        binding.buttonThirdSecond.setOnClickListener {
            navController.navigate(R.id.action_thirdFragment_to_secondFragment)
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