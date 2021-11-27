package com.example.task4

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.task4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            Glide.with(this).load(my_image).into(binding.imageView)
            Log.d(ContentValues.TAG, "Download Image")
        }
    }

    companion object {
        private const val my_image = "https://www.meme-arsenal.com/memes/dea858c26303c031d221b415d75de83b.jpg"
    }
}