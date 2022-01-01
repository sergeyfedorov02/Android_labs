package com.example.executionservice

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    private lateinit var future: Future<*>

    override fun onStart() {
        val executor = (applicationContext as MainApplication).executorService
        future = executor.submit {
            while (!executor.isShutdown) {
                Log.d(TAG, "${Thread.currentThread()} is iterating")
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text = "${secondsElapsed++}"
                }
            }
        }
        super.onStart()

    }

    override fun onStop() {
        future.cancel(true)
        super.onStop()

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