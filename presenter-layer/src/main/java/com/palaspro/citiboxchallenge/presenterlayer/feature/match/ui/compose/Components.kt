package com.palaspro.citiboxchallenge.presenterlayer.feature.match.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.NoDrinks
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.palaspro.citiboxchallenge.domainlayer.model.CharacterBo
import com.palaspro.citiboxchallenge.domainlayer.model.LocationBo
import com.palaspro.citiboxchallenge.domainlayer.model.LocationSummaryBo
import com.palaspro.citiboxchallenge.domainlayer.model.MatchStateBo
import com.palaspro.citiboxchallenge.presenterlayer.base.*
import com.palaspro.citiboxchallenge.presenterlayer.feature.match.viewmodel.MatchViewModel

@Composable
fun MatchScreen(
    navController: NavController,
    viewModel: MatchViewModel,
    modifier: Modifier = Modifier
) {
    val currentCharacter = viewModel.currentCharacter.collectAsState(null).value
    val matchCharacter = viewModel.matchCharacter.collectAsState(null).value
    Column(modifier = modifier) {
        TopAppBar(
            title = {
                Text(
                    text = "Beer Matcher",
                    color = YellowLight,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = fontSizeTitleToolbar
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        tint = YellowLight
                    )
                }
            }
        )
        LazyColumn(contentPadding = PaddingValues(margin16Dp)) {
            val modifierCharacter = Modifier
                .weight(1f)
                .padding(margin8Dp)
            item {
                when (matchCharacter) {
                    is MatchStateBo.Searching -> {
                        Row {
                            CharacterDetail(currentCharacter, BlueDark, modifierCharacter)
                            CharacterDetail(null, BlueLight, modifierCharacter)
                        }
                        Searching()
                    }
                    is MatchStateBo.NotFound -> {
                        Row {
                            CharacterDetail(currentCharacter, BlueDark, modifierCharacter)
                            CharacterDetail(null, Color.LightGray, modifierCharacter)
                        }
                        NotFound()
                    }
                    is MatchStateBo.MatchBeerBo -> {
                        Row {
                            CharacterDetail(currentCharacter, BlueDark, modifierCharacter)
                            CharacterDetail(matchCharacter.characterBo, BlueDark, modifierCharacter)
                        }
                        LocationDetail(matchCharacter.locationBo)
                        DatesDetail(matchCharacter.firstDate, matchCharacter.lastDate)
                        Episodes(matchCharacter.episodesMatch)
                    }
                }
            }

        }
    }
}

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

@Preview
@Composable
fun DatesPreview() {
    DatesDetail(firstDate = "25/12/2017", lastDate = "12/05/2021")
}

@Preview
@Composable
fun EpisodesPreview() {
    Episodes(count = 25)
}

@Preview
@Composable
fun SearchingPreview() {
    Searching()
}

@Preview
@Composable
fun NotFoundPreview() {
    NotFound()
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