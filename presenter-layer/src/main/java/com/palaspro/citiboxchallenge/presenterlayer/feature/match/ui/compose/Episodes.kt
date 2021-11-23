package com.palaspro.citiboxchallenge.presenterlayer.feature.match.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.palaspro.citiboxchallenge.presenterlayer.base.BlueDark
import com.palaspro.citiboxchallenge.presenterlayer.base.margin4Dp
import com.palaspro.citiboxchallenge.presenterlayer.base.margin8Dp

@Composable
fun Episodes(count: Int) {
    Column(modifier = Modifier.padding(margin8Dp)) {
        Text(
            text = "Episodes together",
            color = BlueDark,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = count.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = margin4Dp)
        )
    }
}

@Preview
@Composable
fun EpisodesPreview() {
    Episodes(count = 25)
}