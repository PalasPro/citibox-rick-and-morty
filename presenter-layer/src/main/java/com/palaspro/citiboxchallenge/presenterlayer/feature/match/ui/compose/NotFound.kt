package com.palaspro.citiboxchallenge.presenterlayer.feature.match.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoDrinks
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.palaspro.citiboxchallenge.presenterlayer.base.Red
import com.palaspro.citiboxchallenge.presenterlayer.base.margin4Dp
import com.palaspro.citiboxchallenge.presenterlayer.base.margin8Dp

@Composable
fun NotFound() {
    Column(modifier = Modifier.padding(margin8Dp)) {
        Text(
            text = "Sorry, no match :(",
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            color = Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = margin4Dp)
        )
        Icon(
            imageVector = Icons.Filled.NoDrinks,
            tint = Red,
            contentDescription = "",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
fun NotFoundPreview() {
    NotFound()
}