package com.palaspro.citiboxchallenge.datalayer.datasource.local

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.palaspro.citiboxchallenge.datalayer.DataLayerContract
import com.palaspro.citiboxchallenge.datalayer.datasource.local.room.LocationsDao
import com.palaspro.citiboxchallenge.datalayer.model.ErrorDto
import com.palaspro.citiboxchallenge.datalayer.model.LocationDto
import com.palaspro.citiboxchallenge.datalayer.model.toDto
import com.palaspro.citiboxchallenge.datalayer.model.toEntity

class LocationLocalDataSourceImpl(
    private val locationsDao: LocationsDao
) : DataLayerContract.LocationsDataSource.Local {

    override suspend fun getLocation(code: Int): Either<ErrorDto, LocationDto> =
        locationsDao.getLocation(code)?.toDto()?.right() ?: ErrorDto.NoData.left()


    override suspend fun setLocation(locationDto: LocationDto) {
        locationsDao.insertAll(locationDto.toEntity())
    }
}