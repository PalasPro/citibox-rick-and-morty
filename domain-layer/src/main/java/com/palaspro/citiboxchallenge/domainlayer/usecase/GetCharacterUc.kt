package com.palaspro.citiboxchallenge.domainlayer.usecase

import arrow.core.Either
import com.palaspro.citiboxchallenge.domainlayer.DomainLayerContract
import com.palaspro.citiboxchallenge.domainlayer.model.CharacterBo
import com.palaspro.citiboxchallenge.domainlayer.model.ErrorBo
import com.palaspro.citiboxchallenge.domainlayer.model.ResponseInfoPaginationBo
import kotlinx.coroutines.flow.Flow

const val GET_CHARACTER_UC = "getCharacterUc"

class GetCharacterUc(
    private val repository: DomainLayerContract.CharactersRepository
) : DomainLayerContract.UseCaseFlow<Int, Either<ErrorBo, CharacterBo>> {

    override fun run(params: Int): Flow<Either<ErrorBo, CharacterBo>> =
        repository.getCharacter(params)

}