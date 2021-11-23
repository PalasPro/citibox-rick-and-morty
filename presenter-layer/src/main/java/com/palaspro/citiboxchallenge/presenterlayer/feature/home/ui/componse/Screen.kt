package com.palaspro.citiboxchallenge.presenterlayer.feature.home.ui.componse

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.palaspro.citiboxchallenge.domainlayer.model.CharacterBo
import com.palaspro.citiboxchallenge.presenterlayer.base.YellowLight
import com.palaspro.citiboxchallenge.presenterlayer.base.fontSizeTitleToolbar
import com.palaspro.citiboxchallenge.presenterlayer.base.margin8Dp
import com.palaspro.citiboxchallenge.presenterlayer.feature.home.viewmodel.HomeViewModel

private var initialCharacters = listOf<CharacterBo>()

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val characters = viewModel.characters.collectAsState(initial = initialCharacters).value
    val loadMore = viewModel.loadMore.collectAsState(initial = false).value
    Column {
        TopAppBar(title = {
            Text(
                text = "Rick And Morty Challenge",
                color = YellowLight,
                fontWeight = FontWeight.SemiBold,
                fontSize = fontSizeTitleToolbar
            )
        })
        LazyVerticalGrid(
            cells = GridCells.Fixed(count = 2),
            contentPadding = PaddingValues(horizontal = margin8Dp, vertical = margin8Dp),
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            initialCharacters = characters
            items(characters) {
                CharacterItem(character = it, onClick = { character ->
                    navController.navigate("match/${character.id}")
                })
            }
            if (loadMore) {
                item {
                    LoadingItem()
                    viewModel.loadNextPage()
                }
            }
        }
    }
}