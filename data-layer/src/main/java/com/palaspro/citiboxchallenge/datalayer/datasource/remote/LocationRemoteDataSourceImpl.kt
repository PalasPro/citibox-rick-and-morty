package com.palaspro.citiboxchallenge.datalayer.datasource.remote

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.palaspro.citiboxchallenge.datalayer.DataLayerContract
import com.palaspro.citiboxchallenge.datalayer.model.ErrorDto
import com.palaspro.citiboxchallenge.datalayer.model.LocationDto
import com.palaspro.citiboxchallenge.datalayer.util.safeApiCall

class LocationRemoteDataSourceImpl(
    private val service: RickAndMortyApiRest
) : DataLayerContract.LocationsDataSource.Remote {

    override suspend fun getLocation(code: Int): Either<ErrorDto, LocationDto> =
        safeApiCall {
            val result = service.getLocation(code)
            if (result.isSuccessful) {
                result.body()?.right() ?: ErrorDto.NoData.left()
            } else {
                ErrorDto.ApiCall(result.code()).left()
            }
        } ?: ErrorDto.FatalApiCall.left()
}