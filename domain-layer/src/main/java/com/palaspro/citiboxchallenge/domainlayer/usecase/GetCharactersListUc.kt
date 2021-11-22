package com.palaspro.citiboxchallenge.domainlayer.usecase

import arrow.core.Either
import com.palaspro.citiboxchallenge.domainlayer.DomainLayerContract
import com.palaspro.citiboxchallenge.domainlayer.model.ErrorBo
import com.palaspro.citiboxchallenge.domainlayer.model.ResponseInfoPaginationBo
import kotlinx.coroutines.flow.Flow

const val GET_LIST_CHARACTERS_UC = "getListCharactersUc"

class GetListCharactersUc(
    private val repository: DomainLayerContract.CharactersRepository
) : DomainLayerContract.UseCaseFlow<Any?, Either<ErrorBo, ResponseInfoPaginationBo.Characters>> {

    override fun run(params: Any?): Flow<Either<ErrorBo, ResponseInfoPaginationBo.Characters>> =
        repository.getCharacters()

}