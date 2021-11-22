package com.palaspro.citiboxchallenge.presenterlayer.feature.match.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.palaspro.citiboxchallenge.domainlayer.model.LocationBo
import com.palaspro.citiboxchallenge.presenterlayer.base.BlueDark
import com.palaspro.citiboxchallenge.presenterlayer.base.margin4Dp
import com.palaspro.citiboxchallenge.presenterlayer.base.margin8Dp

@Composable
fun LocationDetail(locationBo: LocationBo?) {
    Column(modifier = Modifier.padding(margin8Dp)) {
        Text(
            text = "Meeting point",
            color = BlueDark,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = locationBo?.name.orEmpty(),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = margin4Dp)
        )
        Text(
            text = locationBo?.dimension.orEmpty(),
            fontSize = 18.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .padding(bottom = margin4Dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun LocationPreview() {
    LocationDetail(
        locationBo = LocationBo(
            id = 1,
            name = "Name Location",
            dimension = "One dimension",
            type = "",
            residents = listOf(),
            url = "",
        )
    )
}