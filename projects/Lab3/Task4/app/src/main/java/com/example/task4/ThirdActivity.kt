package com.example.task4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.example.task4.databinding.ThirdBinding

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
        val intent = Intent(this, FirstActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun moveToSecond() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
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