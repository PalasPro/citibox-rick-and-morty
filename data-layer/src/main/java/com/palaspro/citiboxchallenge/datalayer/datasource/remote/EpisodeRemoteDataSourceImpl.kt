package com.palaspro.citiboxchallenge.datalayer.datasource.remote

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.palaspro.citiboxchallenge.datalayer.DataLayerContract
import com.palaspro.citiboxchallenge.datalayer.model.EpisodeDto
import com.palaspro.citiboxchallenge.datalayer.model.ErrorDto
import com.palaspro.citiboxchallenge.datalayer.model.LocationDto
import com.palaspro.citiboxchallenge.datalayer.util.safeApiCall

class EpisodeRemoteDataSourceImpl(
    private val service: RickAndMortyApiRest
) : DataLayerContract.EpisodesDataSource.Remote {

    override suspend fun getEpisode(code: Int): Either<ErrorDto, EpisodeDto> =
        safeApiCall {
            val result = service.getEpisode(code)
            if (result.isSuccessful) {
                result.body()?.right() ?: ErrorDto.NoData.left()
            } else {
                ErrorDto.ApiCall(result.code()).left()
            }
        } ?: ErrorDto.FatalApiCall.left()

}