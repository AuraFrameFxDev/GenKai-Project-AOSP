package dev.aurakai.auraframefx.core.initialization

import android.app.Application
import android.util.Log
import com.google.ai.client.generativeai.BuildConfig
import dev.aurakai.auraframefx.core.logging.DebugTreeWithClassAndMethod
import timber.log.Timber
import javax.inject.Inject

/**
 * Initializes Timber for application-wide logging.
 * In debug builds, plants a debug tree that shows the class name and method name.
 * In release builds, plants a crash reporting tree that forwards errors to crash reporting tools.
 */
class TimberInitializer @Inject constructor() {

    fun initialize(application: Application) {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTreeWithClassAndMethod())
            Timber.tag("AuraFrameFX")
                .d("Timber initialized in DEBUG mode with class/method logging")
        } else {
            Timber.plant(CrashReportingTree())
            Timber.tag("AuraFrameFX").i("Timber initialized in RELEASE mode with crash reporting")
        }
    }

    /**
     * A tree which logs important information for crash reporting.
     */
    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority < Log.INFO) {
                return
            }

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
