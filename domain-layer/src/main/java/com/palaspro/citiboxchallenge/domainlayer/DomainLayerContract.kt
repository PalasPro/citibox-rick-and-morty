package com.palaspro.citiboxchallenge.domainlayer

import arrow.core.Either
import com.palaspro.citiboxchallenge.domainlayer.model.ErrorBo
import com.palaspro.citiboxchallenge.domainlayer.model.ResponseInfoPaginationBo
import kotlinx.coroutines.flow.Flow

sealed class DomainLayerContract {


    interface CharactersRepository {

        fun getCharacters(): Flow<Either<ErrorBo, ResponseInfoPaginationBo.Characters>>

        suspend fun loadPageCharacters(page: Int): Either<ErrorBo, Boolean>
    }

    interface BaseDomainBridge

    interface UseCase<in R, out T> {
        suspend fun run(params: R): T
    }

    interface UseCaseFlow<in R, out T> {
        fun run(params: R): Flow<T>
    }
}