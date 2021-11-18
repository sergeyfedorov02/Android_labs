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
        binding.bnToFirst.setOnClickListener { moveToFirst() }
        binding.bnToSecond.setOnClickListener { moveToSecond() }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.drawerNavView.setNavigationItemSelectedListener { moveToAbout(it) }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun moveToFirst() {
        this.setResult(Activity.RESULT_OK)
        finish()
    }

    private fun moveToSecond() {
        finish()
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