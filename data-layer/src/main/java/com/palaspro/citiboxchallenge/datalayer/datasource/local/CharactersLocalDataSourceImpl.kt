package com.palaspro.citiboxchallenge.datalayer.datasource.local

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.palaspro.citiboxchallenge.datalayer.DataLayerContract
import com.palaspro.citiboxchallenge.datalayer.datasource.local.room.CharactersDao
import com.palaspro.citiboxchallenge.datalayer.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class CharactersLocalDataSourceImpl(
    private val charactersDao: CharactersDao
) : DataLayerContract.CharactersDataSource.Local {

    override fun getCharacters(): Flow<Either<ErrorDto, ResponseInfoPaginationDto.Characters>> =
        charactersDao.getCharacters().transform { value ->
            if (value.isEmpty()) {
                emit(ErrorDto.NoData.left())
            } else {
                emit(value.toDto().right())
            }
        }

    override fun getCharacter(id: Int): Flow<Either<ErrorDto, CharacterDto>> =
        charactersDao.getCharacter(id).transform { value ->
            value?.let {
                emit(value.toDto().right())
            } ?: run {
                emit(ErrorDto.NoData.left())
            }
        }

    override suspend fun clearPaginationToCharacters() {
        charactersDao.clearPaginationToCharacters()
    }

    override suspend fun getCharacterSync(id: Int): Either<ErrorDto, CharacterDto> =
        charactersDao.getCharacterSync(id)?.toDto()?.right() ?: ErrorDto.NoData.left()

    override suspend fun setCharacter(characters: CharacterDto) {
        charactersDao.insertAll(characters.toEntity())
    }

    override suspend fun setCharacters(
        page: Int,
        characters: ResponseInfoPaginationDto.Characters
    ) {
        charactersDao.insertAll(*characters.toEntity(page).toTypedArray())
    }
}