package com.palaspro.citiboxchallenge.datalayer.datasource.local

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.palaspro.citiboxchallenge.datalayer.DataLayerContract
import com.palaspro.citiboxchallenge.datalayer.datasource.local.room.EpisodesDao
import com.palaspro.citiboxchallenge.datalayer.model.EpisodeDto
import com.palaspro.citiboxchallenge.datalayer.model.ErrorDto
import com.palaspro.citiboxchallenge.datalayer.model.toDto
import com.palaspro.citiboxchallenge.datalayer.model.toEntity

class EpisodeLocalDataSourceImpl(
    private val episodesDao: EpisodesDao
) : DataLayerContract.EpisodesDataSource.Local {

    override suspend fun getEpisode(code: Int): Either<ErrorDto, EpisodeDto> =
        episodesDao.getEpisode(code)?.toDto()?.right() ?: ErrorDto.NoData.left()

    override suspend fun setEpisode(episodeDto: EpisodeDto) {
        episodesDao.insertAll(episodeDto.toEntity())
    }


}