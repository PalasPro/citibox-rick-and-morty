package com.palaspro.citiboxchallenge.datalayer.datasource.remote

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.palaspro.citiboxchallenge.datalayer.DataLayerContract
import com.palaspro.citiboxchallenge.datalayer.model.CharacterDto
import com.palaspro.citiboxchallenge.datalayer.model.ErrorDto
import com.palaspro.citiboxchallenge.datalayer.model.ResponseInfoPaginationDto
import com.palaspro.citiboxchallenge.datalayer.util.safeApiCall

class CharactersRemoteDataSourceImpl(
    private val service: RickAndMortyApiRest
) : DataLayerContract.CharactersDataSource.Remote {

    override suspend fun getCharacters(page: Int): Either<ErrorDto, ResponseInfoPaginationDto.Characters> =
        safeApiCall {
            val result = service.getCharacters(page)
            if (result.isSuccessful) {
                result.body()?.right() ?: ErrorDto.NoData.left()
            } else {
                ErrorDto.ApiCall(result.code()).left()
            }
        } ?: ErrorDto.FatalApiCall.left()

    override suspend fun getCharacter(id: Int): Either<ErrorDto, CharacterDto> =
        safeApiCall {
            val result = service.getCharacter(id)
            if (result.isSuccessful) {
                result.body()?.right() ?: ErrorDto.NoData.left()
            } else {
                ErrorDto.ApiCall(result.code()).left()
            }
        } ?: ErrorDto.FatalApiCall.left()
}