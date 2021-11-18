package com.palaspro.citiboxchallenge.rickandmorty

import android.app.Application
import com.palaspro.citiboxchallenge.datalayer.di.dataLayerModules
import com.palaspro.citiboxchallenge.domainlayer.di.domainLayerModules
import com.palaspro.citiboxchallenge.presenterlayer.di.presenterLayerModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickAndMortyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RickAndMortyApplication)
            modules(
                dataLayerModules +
                        domainLayerModules +
                        presenterLayerModules
            )
        }
    }
}