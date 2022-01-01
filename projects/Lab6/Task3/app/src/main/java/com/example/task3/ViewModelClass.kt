package com.example.task3

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import kotlinx.coroutines.*
import java.net.URL


class ViewModelClass : ViewModel() {

    val mutableLiveData = MutableLiveData<Bitmap>()

    fun downloadImage(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Sleeping 2 sec")
            delay(2000)
            val stream = URL(url).openConnection().getInputStream()
            val bitmap = BitmapFactory.decodeStream(stream)
            withContext(Dispatchers.Main) {
                mutableLiveData.value = bitmap
            }
        }
    }
}