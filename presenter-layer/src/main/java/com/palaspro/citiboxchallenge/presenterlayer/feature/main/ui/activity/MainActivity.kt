package com.palaspro.citiboxchallenge.presenterlayer.feature.main.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.toArgb
import com.palaspro.citiboxchallenge.presenterlayer.base.themeColors
import com.palaspro.citiboxchallenge.presenterlayer.feature.main.ui.compose.MainScreen
import com.palaspro.citiboxchallenge.presenterlayer.feature.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    private fun initViews() {
        setContent {
            MaterialTheme(
                colors = themeColors
            ) {
                window.statusBarColor = themeColors.primary.toArgb()

                val characters = viewModel.characters.collectAsState(initial = listOf()).value
                val loadMore = viewModel.loadMore.collectAsState(initial = false).value
                MainScreen(
                    characters = characters,
                    moreElements = loadMore,
                    onClick = {
                        // TODO
                    },
                    onLoadMore = { viewModel.loadNextPage() })

            }
        }
    }

}