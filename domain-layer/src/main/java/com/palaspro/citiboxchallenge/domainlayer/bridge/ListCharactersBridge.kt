package com.palaspro.citiboxchallenge.domainlayer.bridge

import arrow.core.Either
import com.palaspro.citiboxchallenge.domainlayer.DomainLayerContract
import com.palaspro.citiboxchallenge.domainlayer.model.ErrorBo
import com.palaspro.citiboxchallenge.domainlayer.model.ResponseInfoPaginationBo
import kotlinx.coroutines.flow.Flow

interface ListCharactersBridge : DomainLayerContract.BaseDomainBridge {

    fun getCharacters(): Flow<Either<ErrorBo, ResponseInfoPaginationBo.Characters>>
    suspend fun loadNextPage(page: Int): Either<ErrorBo, Boolean>
}

class ListCharactersBridgeImpl(
    private val getListCharactersUc: DomainLayerContract.UseCaseFlow<Any?, Either<ErrorBo, ResponseInfoPaginationBo.Characters>>,
    private val loadPageCharactersUc: DomainLayerContract.UseCase<Int, Either<ErrorBo, Boolean>>
) : ListCharactersBridge {
    override fun getCharacters(): Flow<Either<ErrorBo, ResponseInfoPaginationBo.Characters>> =
        getListCharactersUc.run(null)

    override suspend fun loadNextPage(page: Int): Either<ErrorBo, Boolean> =
        loadPageCharactersUc.run(page)


}