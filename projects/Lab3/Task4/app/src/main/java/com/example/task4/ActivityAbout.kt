package com.example.task4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.task4.databinding.AboutBinding

class ActivityAbout: AppCompatActivity() {

    private lateinit var binding: AboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAboutThird.setOnClickListener { moveToThird() }
    }

    private fun moveToThird() {
        startActivity(Intent(this, ThirdActivity::class.java))
    }

}