package com.palaspro.citiboxchallenge.presenterlayer.di

import com.palaspro.citiboxchallenge.presenterlayer.feature.home.viewmodel.HomeViewModel
import com.palaspro.citiboxchallenge.presenterlayer.feature.match.viewmodel.MatchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presenterLayerModules = module {

    viewModel {
        HomeViewModel(bridge = get())
    }

    viewModel { (idCharacter: Int) ->
        MatchViewModel(
            idCharacter = idCharacter,
            bridge = get()
        )
    }
}