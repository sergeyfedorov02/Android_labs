package com.example.task4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.example.task4.databinding.SecondBinding

class SecondActivity : AppCompatActivity(){


    private lateinit var binding: SecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSecondFirst.setOnClickListener { moveToFirst() }
        binding.buttonSecondThird.setOnClickListener { moveToThird() }

        binding.navView.setNavigationItemSelectedListener { moveToAbout(it) }

    }

    private fun moveToFirst() {
        finish()
    }

    private fun moveToThird() {
        startActivity(Intent(this, ThirdActivity::class.java))
    }


    private fun moveToAbout(item: MenuItem) : Boolean {
        return if (item.itemId == R.id.nav_message) {
            startActivity(Intent(this, ActivityAbout::class.java))
            true
        } else {
            false
        }
    }

    // Чтобы после возвращения из About закрыть Navigation Drawer
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}