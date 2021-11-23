package com.palaspro.citiboxchallenge.presenterlayer.feature.match.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.palaspro.citiboxchallenge.domainlayer.model.CharacterBo
import com.palaspro.citiboxchallenge.domainlayer.model.LocationSummaryBo
import com.palaspro.citiboxchallenge.presenterlayer.base.*


@Composable
fun CharacterDetail(character: CharacterBo?, color: Color, modifier: Modifier) {
    Column(modifier = modifier) {
        Card(
            backgroundColor = color,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Image(
                    painter = rememberImagePainter(data = character?.image),
                    contentDescription = character?.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(heightItem)
                        .fillMaxWidth()
                )
                Text(
                    text = character?.name.orEmpty(),
                    color = Yellow,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(nameCharacterItem)
                        .padding(
                            margin16Dp
                        )
                )
            }
        }
    }
}

@Preview
@Composable
fun CharacterDetailPreview() {
    CharacterDetail(buildCharacter(), BlueDark, Modifier.fillMaxWidth())
}


private fun buildCharacter() =
    CharacterBo(
        id = 1,
        name = "Rick Sanchez",
        species = "Human",
        type = "Rare",
        gender = "Male",
        origin = buildLocation(),
        status = "Alive",
        location = buildLocation(),
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episode = listOf(1, 2),
        url = "https://rickandmortyapi.com/api/character/1"
    )

private fun buildLocation() =
    LocationSummaryBo(
        name = "Earth",
        code = 20
    )