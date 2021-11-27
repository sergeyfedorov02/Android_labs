package com.example.threads

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView

    private var backgroundThread = Thread {
        while (!Thread.currentThread().isInterrupted) {
            Log.d(TAG, "${Thread.currentThread()} is iterating")
            try {
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text = "${secondsElapsed++}"
                }
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
    }

    override fun onStop() {
        backgroundThread.interrupt()
        super.onStop()

    }

    override fun onStart() {
        backgroundThread.start()
        super.onStart()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onPause() {
        Log.d(TAG,"onPause()")
        super.onPause()
    }

    override fun onResume() {
        Log.d(TAG,"onResume()")
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
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