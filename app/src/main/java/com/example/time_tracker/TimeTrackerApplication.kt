package com.example.time_tracker

import android.app.Application
import com.example.time_tracker.di.AppContainer
import com.example.time_tracker.di.AppContainerImpl
import dagger.hilt.android.HiltAndroidApp

/**
 * @author bybuss
 */
@HiltAndroidApp
class TimeTrackerApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}