package com.palaspro.citiboxchallenge.domainlayer.bridge

import arrow.core.Either
import com.palaspro.citiboxchallenge.domainlayer.DomainLayerContract
import com.palaspro.citiboxchallenge.domainlayer.model.CharacterBo
import com.palaspro.citiboxchallenge.domainlayer.model.ErrorBo
import com.palaspro.citiboxchallenge.domainlayer.model.MatchStateBo
import kotlinx.coroutines.flow.Flow

interface MatchCharacterBridge {

    fun getCharacter(idCharacter: Int): Flow<Either<ErrorBo, CharacterBo>>

    fun bestMatchToBeer(idCharacter: Int): Flow<MatchStateBo>
}

class MatchCharacterBridgeImpl(
    private val getCharacterUc: DomainLayerContract.UseCaseFlow<Int, Either<ErrorBo, CharacterBo>>,
    private val matchBeerUc: DomainLayerContract.UseCaseFlow<Int, MatchStateBo>
) : MatchCharacterBridge {

    override fun getCharacter(idCharacter: Int): Flow<Either<ErrorBo, CharacterBo>> =
        getCharacterUc.run(idCharacter)

    override fun bestMatchToBeer(idCharacter: Int): Flow<MatchStateBo> =
        matchBeerUc.run(idCharacter)

}