package com.palaspro.citiboxchallenge.datalayer.repository

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import com.palaspro.citiboxchallenge.datalayer.DataLayerContract
import com.palaspro.citiboxchallenge.datalayer.model.nextPage
import com.palaspro.citiboxchallenge.datalayer.model.toBo
import com.palaspro.citiboxchallenge.datalayer.model.toDto
import com.palaspro.citiboxchallenge.domainlayer.DomainLayerContract
import com.palaspro.citiboxchallenge.domainlayer.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class CharactersRepositoryImpl(
    private val remote: DataLayerContract.CharactersDataSource.Remote,
    private val local: DataLayerContract.CharactersDataSource.Local
) : DomainLayerContract.CharactersRepository {

    companion object {
        private val requestCharactersConfig = RequestConfigBo()
    }

    override fun getCharacters(): Flow<Either<ErrorBo, ResponseInfoPaginationBo.Characters>> =
        local.getCharacters().transform { value ->
            value.fold(
                ifLeft = {
                    when (it.toBo()) {
                        is ErrorBo.NoData -> {
                            requestCharactersConfig.page = 1
                            emit(
                                ResponseInfoPaginationBo.Characters(
                                    info = InfoPaginationBo(),
                                    results = listOf()
                                ).right()
                            )
                        }
                        else -> emit(it.toBo().left())
                    }
                },
                ifRight = {
                    requestCharactersConfig.page = it.info.next?.nextPage() ?: 1
                    emit(it.toBo().right())
                }
            )
        }

    override fun getCharacter(id: Int): Flow<Either<ErrorBo, CharacterBo>> =
        local.getCharacter(id).transform { value ->
            value.fold(
                // if not found in local, get from remote and save it
                ifLeft = {
                    remote.getCharacter(id).map { character ->
                        local.setCharacter(character)
                        emit(character.toBo().right())
                    }
                },
                ifRight = { emit(it.toBo().right()) }
            )
        }

    override suspend fun getMultipleCharacters(ids: List<Int>): Either<ErrorBo, List<CharacterBo>> =
        ids.mapNotNull { id ->
            getCharacterSync(id).orNull()
        }.right()

    override suspend fun getCharacterSync(id: Int): Either<ErrorBo, CharacterBo> =
        local.getCharacterSync(id).fold(
            // if not found in local, get from remote and save it
            ifLeft = {
                remote.getCharacter(id).flatMap { character ->
                    local.setCharacter(character)
                    character.toBo().right()
                }.mapLeft {
                    it.toBo()
                }
            },
            ifRight = { it.toBo().right() }
        )

    override suspend fun loadNextPageCharacters(): Either<ErrorBo, Boolean> {
        return remote.getCharacters(requestCharactersConfig.toDto()).fold(
            ifLeft = { it.toBo().left() },
            ifRight = { response ->
                local.setCharacters(requestCharactersConfig.page, response)
                (response.info.next != null).right()
            }
        )
    }

    override suspend fun filterCharacters(query: String): Either<ErrorBo, Boolean> =
        if (requestCharactersConfig.query != query) {
            // reset request config
            requestCharactersConfig.page = 1
            requestCharactersConfig.query = query
            // clear current pagination
            local.clearPaginationToCharacters()
            loadNextPageCharacters()
            true.right()
        } else {
            false.right()
        }

}