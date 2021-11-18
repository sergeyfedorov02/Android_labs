package com.example.task3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task3.databinding.ThirdFragmentBinding

class ThirdFragment : Fragment() {

    lateinit var binding : ThirdFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = ThirdFragmentBinding.inflate(layoutInflater)
        val navController = findNavController()

        binding.bnToFirst.setOnClickListener {
            navController.navigate(R.id.action_thirdFragment_to_firstFragment)
        }

        binding.bnToSecond.setOnClickListener {
            navController.navigate(R.id.action_thirdFragment_to_secondFragment)
        }

        binding.drawerNavView.setNavigationItemSelectedListener{ moveToAbout(it) }

        return binding.root
    }

    private fun moveToAbout(item: MenuItem) : Boolean {

        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START)}

        return if (item.itemId == R.id.aboutActivity) {
            findNavController().navigate(R.id.global_about)
            true
        } else {
            false
        }
    }
}