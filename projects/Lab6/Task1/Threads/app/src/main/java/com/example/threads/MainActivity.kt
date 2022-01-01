package com.example.threads

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView

    private lateinit var backgroundThread: Thread

    private fun createNewThread() = Thread {
        try {
            while (!Thread.currentThread().isInterrupted) {
                Log.d(TAG, "${Thread.currentThread()} is iterating")
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text = "${secondsElapsed++}"
                }
            }
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }


    override fun onStop() {
        super.onStop()
        backgroundThread.interrupt()

    }

    override fun onStart() {
        Log.d(TAG, "onStart()")
        super.onStart()
        backgroundThread = createNewThread()
        backgroundThread.start()

        Log.d(TAG, "${backgroundThread.id}")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onPause() {
        Log.d(TAG, "onPause()")
        super.onPause()
    }

    override fun onResume() {
        Log.d(TAG, "onResume()")
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.run {
            putInt(SEC, secondsElapsed)
            Log.d(TAG, "Saving SEC=$secondsElapsed")
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.run {
            secondsElapsed = getInt(SEC)
            Log.d(TAG, "Restore SEC=$secondsElapsed")
        }
    }

    companion object {
        const val TAG = "ContinueWatch"
        const val SEC = "SecondsElapsed"
    }

}