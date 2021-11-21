package com.palaspro.citiboxchallenge.datalayer.repository

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.palaspro.citiboxchallenge.datalayer.DataLayerContract
import com.palaspro.citiboxchallenge.datalayer.model.toBo
import com.palaspro.citiboxchallenge.domainlayer.DomainLayerContract
import com.palaspro.citiboxchallenge.domainlayer.model.EpisodeBo
import com.palaspro.citiboxchallenge.domainlayer.model.ErrorBo

class EpisodeRepositoryImpl(
    private val remote: DataLayerContract.EpisodesDataSource.Remote,
    private val local: DataLayerContract.EpisodesDataSource.Local
) : DomainLayerContract.EpisodeRepository {

    override suspend fun getEpisode(code: Int): Either<ErrorBo, EpisodeBo> =
        local.getEpisode(code).fold(
            ifLeft = {
                remote.getEpisode(code).flatMap { episode ->
                    local.setEpisode(episode)
                    episode.toBo().right()
                }.mapLeft { it.toBo() }
            },
            ifRight = {
                it.toBo().right()
            }
        )
}