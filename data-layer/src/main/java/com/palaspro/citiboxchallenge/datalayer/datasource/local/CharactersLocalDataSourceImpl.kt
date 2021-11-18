package com.palaspro.citiboxchallenge.datalayer.datasource.local

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.palaspro.citiboxchallenge.datalayer.DataLayerContract
import com.palaspro.citiboxchallenge.datalayer.datasource.local.room.CharactersDao
import com.palaspro.citiboxchallenge.datalayer.model.ErrorDto
import com.palaspro.citiboxchallenge.datalayer.model.ResponseInfoPaginationDto
import com.palaspro.citiboxchallenge.datalayer.model.toDto
import com.palaspro.citiboxchallenge.datalayer.model.toEntity
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

    override suspend fun setCharacters(
        page: Int,
        characters: ResponseInfoPaginationDto.Characters
    ) {
        charactersDao.insertAll(*characters.toEntity(page).toTypedArray())
    }
}