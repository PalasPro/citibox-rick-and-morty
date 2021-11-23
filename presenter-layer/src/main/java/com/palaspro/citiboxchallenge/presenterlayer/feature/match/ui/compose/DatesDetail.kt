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
fun DatesDetail(firstDate: String?, lastDate: String?) {
    Column(modifier = Modifier.padding(margin8Dp)) {
        Text(
            text = "Their moments",
            color = BlueDark,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "First time",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = margin4Dp)
        )
        Text(
            text = firstDate.orEmpty(),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = margin4Dp)
        )
        Text(
            text = "Last time",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = margin4Dp)
        )
        Text(
            text = lastDate.orEmpty(),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = margin4Dp)
        )
    }
}

@Preview
@Composable
fun DatesPreview() {
    DatesDetail(firstDate = "25/12/2017", lastDate = "12/05/2021")
}
