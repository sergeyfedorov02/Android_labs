package com.example.task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.example.task3.databinding.SecondBinding

class SecondActivity : AppCompatActivity(){


    private lateinit var binding: SecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bnToFirst.setOnClickListener { moveToFirst() }
        binding.bnToThird.setOnClickListener { moveToThird() }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.drawerNavView.setNavigationItemSelectedListener { moveToAbout(it) }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun moveToFirst() {
        finish()
    }

    private fun moveToThird() {
        startActivity(Intent(this, ThirdActivity::class.java))
    }


    private fun moveToAbout(item: MenuItem) : Boolean {

        // Чтобы после возвращения из About Navigation Drawer был закрыт
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START)}

        return if (item.itemId == R.id.aboutActivity) {
            startActivity(Intent(this, ActivityAbout::class.java))
            true
        } else {
            false
        }
    }
}