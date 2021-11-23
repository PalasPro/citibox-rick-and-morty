package com.palaspro.citiboxchallenge.presenterlayer.feature.home.ui.componse

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.palaspro.citiboxchallenge.presenterlayer.base.Green
import com.palaspro.citiboxchallenge.presenterlayer.base.margin8Dp

@Composable
fun LoadingItem(modifier: Modifier = Modifier) {
    Text(
        text = "Loading...",
        color = Green,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = modifier
            .padding(margin8Dp)
            .fillMaxWidth()
    )
}