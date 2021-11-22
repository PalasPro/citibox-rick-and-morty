package com.palaspro.citiboxchallenge.presenterlayer.feature.match.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.palaspro.citiboxchallenge.presenterlayer.base.Orange
import com.palaspro.citiboxchallenge.presenterlayer.base.margin4Dp
import com.palaspro.citiboxchallenge.presenterlayer.base.margin8Dp

@Composable
fun Searching() {
    Column(modifier = Modifier.padding(margin8Dp)) {
        Text(
            text = "Searching the best match...",
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            color = Orange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = margin4Dp)
        )
        CircularProgressIndicator(
            color = Orange,
            strokeWidth = margin4Dp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
fun SearchingPreview() {
    Searching()
}