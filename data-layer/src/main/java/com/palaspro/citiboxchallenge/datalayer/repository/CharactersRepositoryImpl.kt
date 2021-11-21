package com.palaspro.citiboxchallenge.datalayer.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.palaspro.citiboxchallenge.datalayer.DataLayerContract
import com.palaspro.citiboxchallenge.datalayer.model.toBo
import com.palaspro.citiboxchallenge.domainlayer.DomainLayerContract
import com.palaspro.citiboxchallenge.domainlayer.model.ErrorBo
import com.palaspro.citiboxchallenge.domainlayer.model.ResponseInfoPaginationBo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class CharactersRepositoryImpl(
    private val remote: DataLayerContract.CharactersDataSource.Remote,
    private val local: DataLayerContract.CharactersDataSource.Local
) : DomainLayerContract.CharactersRepository {

    override fun getCharacters(): Flow<Either<ErrorBo, ResponseInfoPaginationBo.Characters>> =
        local.getCharacters().transform { value ->
            value.fold(
                ifLeft = { emit(it.toBo().left()) },
                ifRight = { emit(it.toBo().right()) }
            )
        }


    override suspend fun loadPageCharacters(page: Int): Either<ErrorBo, Boolean> =
        remote.getCharacters(page).fold(
            ifLeft = { it.toBo().left() },
            ifRight = { response ->
                local.setCharacters(page, response)
                (response.info.next != null).right()
            }
        )

}