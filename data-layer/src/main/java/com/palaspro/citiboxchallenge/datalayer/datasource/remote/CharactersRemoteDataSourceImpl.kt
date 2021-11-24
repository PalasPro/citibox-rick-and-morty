package com.palaspro.citiboxchallenge.datalayer.datasource.remote

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.palaspro.citiboxchallenge.datalayer.DataLayerContract
import com.palaspro.citiboxchallenge.datalayer.model.CharacterDto
import com.palaspro.citiboxchallenge.datalayer.model.ErrorDto
import com.palaspro.citiboxchallenge.datalayer.model.RequestConfigDto
import com.palaspro.citiboxchallenge.datalayer.model.ResponseInfoPaginationDto
import com.palaspro.citiboxchallenge.datalayer.util.safeApiCall

private const val QUERY_PAGE = "page"
private const val QUERY_NAME = "name"

class CharactersRemoteDataSourceImpl(
    private val service: RickAndMortyApiRest
) : DataLayerContract.CharactersDataSource.Remote {

    override suspend fun getCharacters(requestConfig: RequestConfigDto): Either<ErrorDto, ResponseInfoPaginationDto.Characters> =
        safeApiCall {
            val map = mutableMapOf<String, String>().apply {
                put(QUERY_PAGE, requestConfig.page.toString())
                requestConfig.query?.let { query ->
                    if (query.isNotEmpty()) {
                        put(QUERY_NAME, query)
                    }
                }
            }
            val result = service.getCharacters(map)
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