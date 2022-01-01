package com.example.task2

import android.app.Application
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.net.URL
import java.util.concurrent.ExecutorService

class ViewModelClass(application: Application) : AndroidViewModel(application) {

    private val executor: ExecutorService = getApplication<MainApplication>().executorService
    val mutableLiveData = MutableLiveData<Bitmap>()

    fun downloadImage(url: String) {
        executor.execute {
            Log.d(ContentValues.TAG, "Thread sleeping 2 sec")
            Thread.sleep(2000)
            val stream = URL(url).openConnection().getInputStream()
            val bitmap = BitmapFactory.decodeStream(stream)
            mutableLiveData.postValue(bitmap)
        }
    }
}