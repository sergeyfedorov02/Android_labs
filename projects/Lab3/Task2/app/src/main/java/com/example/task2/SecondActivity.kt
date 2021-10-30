package com.example.task2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.example.task2.databinding.SecondBinding

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
        startActivityForResult(Intent(this, ThirdActivity::class.java), RESULT_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_CODE && resultCode == Activity.RESULT_OK) {
            finish()
        }
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

    companion object {
        const val RESULT_CODE = 0
    }
}