package com.palaspro.citiboxchallenge.presenterlayer.feature.home.ui.componse

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.palaspro.citiboxchallenge.presenterlayer.feature.home.viewmodel.HomeViewModel

@Composable
fun SearchForm(viewModel: HomeViewModel) {
    var query by remember { mutableStateOf("") }
    TextField(
        value = query,
        onValueChange = {
            query = it
            viewModel.filterCharacters(query)
        },
        singleLine = true,
        label = { Text("Search by name") },
        modifier = Modifier.fillMaxWidth()
    )
}