package com.palaspro.citiboxchallenge.presenterlayer.feature.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palaspro.citiboxchallenge.domainlayer.bridge.ListCharactersBridge
import com.palaspro.citiboxchallenge.domainlayer.model.CharacterBo
import com.palaspro.citiboxchallenge.domainlayer.model.ErrorBo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

class HomeViewModel(
    private val bridge: ListCharactersBridge
) : ViewModel() {

    val characters: Flow<List<CharacterBo>> = bridge.getCharacters().transform { value ->
        value.fold(
            ifLeft = {
                handleError(it)
            },
            ifRight = {
                emit(it.results)
                loadMore = it.info.hasNext
            }
        )
    }

    var loadMore by mutableStateOf(false)
        private set

    init {
        filterCharacters()
    }

    fun loadNextPage() {
        viewModelScope.launch(Dispatchers.IO) {
            bridge.loadNextPage()
                .fold(ifLeft = { handleErrorNextPage(it) }, ifRight = { handleSuccessNextPage(it) })
        }
    }

    fun filterCharacters(query: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            bridge.filterCharacters(query)
        }
    }

    private fun handleSuccessNextPage(morePages: Boolean) {
        viewModelScope.launch {
            loadMore = morePages
        }
    }

    private fun handleErrorNextPage(error: ErrorBo) {
        when (error) {
            is ErrorBo.NoData -> Unit
            is ErrorBo.UnKnown -> Unit
            is ErrorBo.ApiCall -> Unit
        }
    }

    private fun handleError(error: ErrorBo) {
        when (error) {
            is ErrorBo.NoData -> Unit
            is ErrorBo.UnKnown -> Unit
            is ErrorBo.ApiCall -> Unit
        }
    }

}