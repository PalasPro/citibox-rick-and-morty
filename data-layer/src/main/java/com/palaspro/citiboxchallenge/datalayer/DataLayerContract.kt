package com.palaspro.citiboxchallenge.datalayer

import arrow.core.Either
import com.palaspro.citiboxchallenge.datalayer.model.ErrorDto
import com.palaspro.citiboxchallenge.datalayer.model.ResponseInfoPaginationDto
import kotlinx.coroutines.flow.Flow

sealed class DataLayerContract {

    sealed class CharactersDataSource {

        interface Remote {
            suspend fun getCharacters(page: Int): Either<ErrorDto, ResponseInfoPaginationDto.Characters>
        }

        interface Local {
            fun getCharacters(): Flow<Either<ErrorDto, ResponseInfoPaginationDto.Characters>>

            suspend fun setCharacters(page: Int, characters: ResponseInfoPaginationDto.Characters)
        }
    }

}