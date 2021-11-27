package com.example.task2

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ViewModelClass : ViewModel() {

    private lateinit var executorService: ExecutorService
    val mutableLiveData = MutableLiveData<Bitmap>()

    fun downloadImage(url: String) {
        executorService = Executors.newSingleThreadExecutor()
        executorService.execute {
            Log.d(ContentValues.TAG, "Thread sleeping 2 sec")
            Thread.sleep(2000)
            val stream = URL(url).openConnection().getInputStream()
            val bitmap = BitmapFactory.decodeStream(stream)
            mutableLiveData.postValue(bitmap)
        }
    }

    override fun onCleared() {
        executorService.shutdown()
        super.onCleared()
    }
}