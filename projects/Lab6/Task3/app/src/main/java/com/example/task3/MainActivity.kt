package com.example.task3

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.task3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = ViewModelClass()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            viewModel.downloadImage(my_image)
            Log.d(TAG, "Download Image")
        }

        viewModel.mutableLiveData.observe(this) {
            binding.imageView.setImageBitmap(it)
            Log.d(TAG, "Set Image")
        }

    }

    companion object {
        private const val my_image =
            "https://www.meme-arsenal.com/memes/dea858c26303c031d221b415d75de83b.jpg"
    }

}