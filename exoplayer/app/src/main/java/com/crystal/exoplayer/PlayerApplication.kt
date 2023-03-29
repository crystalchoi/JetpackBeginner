package com.crystal.exoplayer

import android.app.Application
import com.crystal.exoplayer.data.AppContainer
import com.crystal.exoplayer.data.DefaultAppContainer


class PlayerApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}