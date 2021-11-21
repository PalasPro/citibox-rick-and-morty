package com.palaspro.citiboxchallenge.presenterlayer.feature.main.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.palaspro.citiboxchallenge.presenterlayer.base.themeColors
import com.palaspro.citiboxchallenge.presenterlayer.feature.main.ui.compose.MainScreen

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    private fun initViews() {
        setContent {
            val navController = rememberNavController()
            MaterialTheme(
                colors = themeColors
            ) {
                window.statusBarColor = themeColors.primary.toArgb()
                MainScreen(navController = navController)
            }
        }
    }

}