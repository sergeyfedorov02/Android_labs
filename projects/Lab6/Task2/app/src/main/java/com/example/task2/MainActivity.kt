package com.example.task2

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.task2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModelClass by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            viewModel.downloadImage(my_image)
            Log.d(ContentValues.TAG, "Download Image")
        }

        viewModel.mutableLiveData.observe(this) {
            binding.imageView.setImageBitmap(it)
            Log.d(ContentValues.TAG, "Set Image")
        }

    }

    companion object {
        private const val my_image = "https://www.meme-arsenal.com/memes/dea858c26303c031d221b415d75de83b.jpg"
    }

}