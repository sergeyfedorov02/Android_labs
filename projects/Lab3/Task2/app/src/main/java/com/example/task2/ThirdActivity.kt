package com.example.task2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.example.task2.databinding.ThirdBinding

class ThirdActivity : AppCompatActivity(){

    private lateinit var binding: ThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonThirdFirst.setOnClickListener { moveToFirst() }
        binding.buttonThirdSecond.setOnClickListener { moveToSecond() }

        binding.navView.setNavigationItemSelectedListener { moveToAbout(it) }

    }

    private fun moveToFirst() {
        this.setResult(Activity.RESULT_OK)
        finish()
    }

    private fun moveToSecond() {
       finish()
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