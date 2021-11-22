package com.palaspro.citiboxchallenge.datalayer

import arrow.core.Either
import com.palaspro.citiboxchallenge.datalayer.model.*
import kotlinx.coroutines.flow.Flow

sealed class DataLayerContract {

    sealed class CharactersDataSource {

        interface Remote {
            suspend fun getCharacters(page: Int): Either<ErrorDto, ResponseInfoPaginationDto.Characters>
            suspend fun getCharacter(id: Int): Either<ErrorDto, CharacterDto>
        }

        interface Local {
            fun getCharacters(): Flow<Either<ErrorDto, ResponseInfoPaginationDto.Characters>>
            fun getCharacter(id: Int): Flow<Either<ErrorDto, CharacterDto>>

            suspend fun getCharacterSync(id: Int): Either<ErrorDto, CharacterDto>
            suspend fun setCharacter(characters: CharacterDto)
            suspend fun setCharacters(page: Int, characters: ResponseInfoPaginationDto.Characters)
        }
    }

    sealed class LocationsDataSource {

        interface Remote {
            suspend fun getLocation(code: Int): Either<ErrorDto, LocationDto>
        }

        interface Local {
            suspend fun getLocation(code: Int): Either<ErrorDto, LocationDto>

            suspend fun setLocation(locationDto: LocationDto)
        }
    }

    sealed class EpisodesDataSource {

        interface Remote {
            suspend fun getEpisode(code: Int): Either<ErrorDto, EpisodeDto>
        }

        interface Local {
            suspend fun getEpisode(code: Int): Either<ErrorDto, EpisodeDto>

            suspend fun setEpisode(episodeDto: EpisodeDto)
        }
    }

}