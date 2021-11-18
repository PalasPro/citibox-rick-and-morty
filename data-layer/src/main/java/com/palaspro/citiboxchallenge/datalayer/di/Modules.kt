package com.palaspro.citiboxchallenge.datalayer.di

import org.koin.dsl.module

private val dataSourceModules = module { }
private val repositoryModules = module { }

val dataLayerModules = listOf(dataSourceModules, repositoryModules)