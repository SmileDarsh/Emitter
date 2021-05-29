package com.yello.emitter

import android.app.Application
import com.yello.emitter.di.modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by µðšţãƒâ ™ on 27/05/2021.
 *  ->
 */
class EmitterApplication  : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EmitterApplication)
            androidLogger()
            modules(modules)
        }
    }
}