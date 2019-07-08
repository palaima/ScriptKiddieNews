package io.palaima.android.scriptkiddienews

import android.app.Application
import timber.log.Timber

@OpenClassOnDebug
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}