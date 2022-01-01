package com.example.coroutines

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)

        lifecycleScope.launchWhenResumed {
            Log.d(TAG, "Launch of Coroutine ")
            while (isActive) {
                Log.d(TAG, "Coroutine is working")
                delay(1000)
                textSecondsElapsed.text = "${secondsElapsed++}"

            }
        }

        Log.d(TAG, "Completion of Coroutine")

    }


    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt(SEC,secondsElapsed)
            Log.d(TAG,"Saving SEC=$secondsElapsed")
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.run {
            secondsElapsed = getInt(SEC)
            Log.d(TAG,"Restore SEC=$secondsElapsed")
        }
    }

    companion object{
        const val TAG = "ContinueWatch"
        const val SEC = "SecondsElapsed"
    }

}