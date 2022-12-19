package com.example.photogallerypoview

import android.app.Application
import com.example.photogallerypoview.model.PreferencesRepository

class PhotoGalleryApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        PreferencesRepository.initialize(this)
    }
}