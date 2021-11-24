package com.palaspro.citiboxchallenge.domainlayer.usecase

import arrow.core.Either
import com.palaspro.citiboxchallenge.domainlayer.DomainLayerContract
import com.palaspro.citiboxchallenge.domainlayer.model.ErrorBo

const val FILTER_CHARACTERS_UC = "filterCharactersUc"

class FilterCharactersUc(
    private val repository: DomainLayerContract.CharactersRepository
) : DomainLayerContract.UseCase<String, Either<ErrorBo, Boolean>> {

    override suspend fun run(params: String): Either<ErrorBo, Boolean> =
        repository.filterCharacters(query = params)


}