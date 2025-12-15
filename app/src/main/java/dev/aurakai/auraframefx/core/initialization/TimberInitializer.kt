package dev.aurakai.auraframefx.core.initialization

import android.app.Application

interface TimberInitializer {
    fun init(app: Application)
    fun logHealthMetric(string: String, eventJson: String)
}

class TimberInitializerImpl : TimberInitializer {
    override fun init(app: Application) {
        Timber.plant(Timber.DebugTree())
    }

    override fun logHealthMetric(string: String, eventJson: String) {
        // No-op in debug builds
    }
}
