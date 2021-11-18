package com.palaspro.citiboxchallenge.presenterlayer.di

import com.palaspro.citiboxchallenge.presenterlayer.feature.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presenterLayerModules = module {

    viewModel {
        MainViewModel(bridge = get())
    }
}