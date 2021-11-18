package com.example.task3

import android.os.Bundle
import android.view.*
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task3.databinding.SecondFragmentBinding

class SecondFragment : Fragment() {

    lateinit var binding : SecondFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = SecondFragmentBinding.inflate(layoutInflater)
        val navController = findNavController()

        binding.bnToFirst.setOnClickListener {
            navController.navigate(R.id.action_secondFragment_to_firstFragment)
        }

        binding.bnToThird.setOnClickListener {
            navController.navigate(R.id.action_secondFragment_to_thirdFragment)
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