package com.example.task3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task3.databinding.FirstFragmentBinding

class FirstFragment : Fragment() {

    lateinit var binding : FirstFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FirstFragmentBinding.inflate(layoutInflater)
        val navController = findNavController()

        binding.bnToSecond.setOnClickListener {
            navController.navigate(R.id.action_firstFragment_to_secondFragment)
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