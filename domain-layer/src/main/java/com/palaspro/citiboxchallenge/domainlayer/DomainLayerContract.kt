package com.palaspro.citiboxchallenge.domainlayer

import arrow.core.Either
import com.palaspro.citiboxchallenge.domainlayer.model.*
import kotlinx.coroutines.flow.Flow

sealed class DomainLayerContract {


    interface CharactersRepository {

        fun getCharacters(): Flow<Either<ErrorBo, ResponseInfoPaginationBo.Characters>>
        fun getCharacter(id: Int): Flow<Either<ErrorBo, CharacterBo>>

        suspend fun getMultipleCharacters(ids: List<Int>): Either<ErrorBo, List<CharacterBo>>
        suspend fun getCharacterSync(id: Int): Either<ErrorBo, CharacterBo>
        suspend fun loadPageCharacters(page: Int): Either<ErrorBo, Boolean>
    }

    interface LocationRepository {

        suspend fun getLocation(code: Int): Either<ErrorBo, LocationBo>
    }

    interface EpisodeRepository {

        suspend fun getEpisode(code: Int): Either<ErrorBo, EpisodeBo>
    }

    interface BaseDomainBridge

    interface UseCase<in R, out T> {
        suspend fun run(params: R): T
    }

    interface UseCaseFlow<in R, out T> {
        fun run(params: R): Flow<T>
    }
}