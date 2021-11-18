package com.palaspro.citiboxchallenge.presenterlayer.feature.main.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.palaspro.citiboxchallenge.domainlayer.model.CharacterBo
import com.palaspro.citiboxchallenge.domainlayer.model.LocationSummaryBo
import com.palaspro.citiboxchallenge.presenterlayer.base.*

@ExperimentalFoundationApi
@Composable
fun MainScreen(
    characters: List<CharacterBo>,
    moreElements: Boolean,
    onClick: (CharacterBo) -> Unit,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        TopAppBar {
            Text(
                text = "Rick And Morty Challenge",
                color = YellowLight,
                fontWeight = FontWeight.SemiBold,
                fontSize = fontSizeTitleToolbar,
                modifier = Modifier.padding(margin16Dp)
            )
        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(count = 2),
            contentPadding = PaddingValues(horizontal = margin8Dp, vertical = margin8Dp),
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            items(characters) {
                CharacterItem(character = it, onClick = onClick)
            }
            if (moreElements) {
                item {
                    Text(
                        text = "Loading...",
                        color = Green,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(margin8Dp)
                            .fillMaxWidth()
                    )
                    onLoadMore.invoke()
                }
            }
        }
    }
}

@Composable
fun CharacterItem(
    character: CharacterBo,
    onClick: (CharacterBo) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier
        .padding(margin8Dp)
        .clickable { onClick.invoke(character) }) {
        Column(modifier = modifier) {
            Image(
                painter = rememberImagePainter(data = character.image),
                contentDescription = character.name,
                modifier = Modifier
                    .height(heightItem)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(horizontal = margin8Dp, vertical = margin4Dp)) {
                Text(
                    text = character.name,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = character.species,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = character.type.orEmpty(),
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCharacterItem() {
    CharacterItem(
        character = buildCharacter(),
        onClick = {}
    )
}

@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen(
        characters = listOf(
            buildCharacter(),
            buildCharacter(),
            buildCharacter(),
            buildCharacter(),
            buildCharacter(),
            buildCharacter(),
            buildCharacter(),
            buildCharacter()
        ),
        moreElements = false,
        onClick = {},
        onLoadMore = {}
    )
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
        url = "https://rickandmortyapi.com/api/character/1",
        created = "2017-11-04T18:48:46.250Z"
    )

private fun buildLocation() =
    LocationSummaryBo(
        name = "Earth",
        code = 20
    )