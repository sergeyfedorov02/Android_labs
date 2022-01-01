package com.example.executionservice

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainApplication : Application() {
    val executorService: ExecutorService = Executors.newFixedThreadPool(1)
}