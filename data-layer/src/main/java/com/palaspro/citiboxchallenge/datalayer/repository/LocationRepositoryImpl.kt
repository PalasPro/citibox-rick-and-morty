package com.palaspro.citiboxchallenge.datalayer.repository

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.palaspro.citiboxchallenge.datalayer.DataLayerContract
import com.palaspro.citiboxchallenge.datalayer.model.toBo
import com.palaspro.citiboxchallenge.domainlayer.DomainLayerContract
import com.palaspro.citiboxchallenge.domainlayer.model.ErrorBo
import com.palaspro.citiboxchallenge.domainlayer.model.LocationBo

class LocationRepositoryImpl(
    private val remote: DataLayerContract.LocationsDataSource.Remote,
    private val local: DataLayerContract.LocationsDataSource.Local
) : DomainLayerContract.LocationRepository {

    override suspend fun getLocation(code: Int): Either<ErrorBo, LocationBo> =
        local.getLocation(code).fold(
            ifLeft = {
                remote.getLocation(code).flatMap { location ->
                    local.setLocation(location)
                    location.toBo().right()
                }.mapLeft { it.toBo() }
            },
            ifRight = {
                it.toBo().right()
            }
        )
}