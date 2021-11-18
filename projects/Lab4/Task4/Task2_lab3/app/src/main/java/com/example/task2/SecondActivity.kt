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
        startActivityForResult(Intent(this, ThirdActivity::class.java), RESULT_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_CODE && resultCode == Activity.RESULT_OK) {
            finish()
        }
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

    companion object {
        const val RESULT_CODE = 0
    }
}