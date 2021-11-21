package com.palaspro.citiboxchallenge.presenterlayer.feature.match.viewmodel

import androidx.lifecycle.ViewModel
import com.palaspro.citiboxchallenge.domainlayer.bridge.MatchCharacterBridge
import com.palaspro.citiboxchallenge.domainlayer.model.CharacterBo
import com.palaspro.citiboxchallenge.domainlayer.model.MatchStateBo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class MatchViewModel(
    idCharacter: Int,
    bridge: MatchCharacterBridge
) : ViewModel() {

    val currentCharacter: Flow<CharacterBo> = bridge.getCharacter(idCharacter).transform {
        it.fold(
            ifLeft = {},
            ifRight = { character -> emit(character) }
        )
    }

    val matchCharacter: Flow<MatchStateBo> = bridge.bestMatchToBeer(idCharacter = idCharacter)

}