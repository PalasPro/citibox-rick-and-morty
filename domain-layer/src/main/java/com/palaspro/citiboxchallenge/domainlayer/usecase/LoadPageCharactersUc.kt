package com.palaspro.citiboxchallenge.domainlayer.usecase

import arrow.core.Either
import com.palaspro.citiboxchallenge.domainlayer.DomainLayerContract
import com.palaspro.citiboxchallenge.domainlayer.model.ErrorBo

const val LOAD_PAGE_CHARACTERS_UC = "loadPageCharactersUc"

class LoadPageCharactersUc(
    private val repository: DomainLayerContract.CharactersRepository
) : DomainLayerContract.UseCase<Int, Either<ErrorBo, Boolean>> {

    override suspend fun run(params: Int): Either<ErrorBo, Boolean> =
        repository.loadPageCharacters(page = params)


}