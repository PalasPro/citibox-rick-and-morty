package com.palaspro.citiboxchallenge.presenterlayer.feature.match.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
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
