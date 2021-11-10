package com.hallow.interview.scenes

import androidx.appcompat.app.AppCompatDelegate
import com.hallow.interview.di.DaggerAppComponent
import dagger.android.DaggerApplication
import nl.komponents.kovenant.android.startKovenant

class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        startKovenant()
    }

    override fun applicationInjector() =
        DaggerAppComponent.builder()
            .application(this)
            .build()

    companion object {
        lateinit var instance: BaseApplication
            private set
    }
}
