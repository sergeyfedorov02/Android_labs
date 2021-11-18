package com.example.task3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.task3.databinding.AboutBinding

class ActivityAbout: AppCompatActivity() {

    private lateinit var binding: AboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}