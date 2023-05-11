package com.samad_talukder.spectrumassessment.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SpectrumApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}