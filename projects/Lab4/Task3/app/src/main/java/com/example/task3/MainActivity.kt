package com.example.task3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import com.example.task3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.drawerNavView.setNavigationItemSelectedListener{ moveToAbout(it) }

        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun moveToAbout(item: MenuItem) : Boolean {

        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START)}

        return if (item.itemId == R.id.aboutActivity) {
            findNavController(R.id.nav_host).navigate(R.id.global_about)
            true
        } else {
            false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}