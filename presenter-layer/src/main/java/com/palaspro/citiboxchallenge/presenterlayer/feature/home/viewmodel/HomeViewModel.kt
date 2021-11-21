package com.palaspro.citiboxchallenge.presenterlayer.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palaspro.citiboxchallenge.domainlayer.bridge.ListCharactersBridge
import com.palaspro.citiboxchallenge.domainlayer.model.CharacterBo
import com.palaspro.citiboxchallenge.domainlayer.model.ErrorBo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
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
                nextPage = it.info.next ?: -1
                _loadMore.value = (nextPage != -1)
                emit(it.results)
            }
        )
    }

    private val _loadMore: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadMore: SharedFlow<Boolean> = _loadMore

    private var nextPage: Int = 1

    fun loadNextPage() {
        viewModelScope.launch(Dispatchers.IO) {
            bridge.loadNextPage(page = nextPage)
                .fold(ifLeft = { handleError(it) }, ifRight = { handleSuccessNextPage(it) })
        }
    }

    private fun handleSuccessNextPage(morePages: Boolean) {
        viewModelScope.launch {
            _loadMore.value = morePages
        }
    }

    private fun handleError(errorBo: ErrorBo) {
        when (errorBo) {
            is ErrorBo.NoData -> loadNextPage()
            is ErrorBo.UnKnown -> Unit
            is ErrorBo.ApiCall -> Unit
        }
    }

}