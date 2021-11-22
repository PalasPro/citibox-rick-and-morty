package com.palaspro.citiboxchallenge.datalayer.model

import android.net.Uri
import com.palaspro.citiboxchallenge.domainlayer.model.*

fun ErrorDto.toBo(): ErrorBo =
    when (this) {
        is ErrorDto.FatalApiCall -> ErrorBo.ApiCall(-1)
        is ErrorDto.ApiCall -> ErrorBo.ApiCall(errorCode)
        is ErrorDto.NoData -> ErrorBo.NoData
        is ErrorDto.UnKnown -> ErrorBo.UnKnown
    }

fun ResponseInfoPaginationDto.Characters.toBo(): ResponseInfoPaginationBo.Characters =
    ResponseInfoPaginationBo.Characters(
        info = info.toBo(),
        results = results.toBo()
    )

fun InfoPaginationDto.toBo(): InfoPaginationBo =
    InfoPaginationBo(
        count = count,
        pages = pages,
        next = next?.let { Uri.parse(it).getQueryParameter("page")?.toInt() },
        prev = prev?.let { Uri.parse(it).getQueryParameter("page")?.toInt() }
    )

fun List<CharacterDto>.toBo(): List<CharacterBo> =
    this.map {
        it.toBo()
    }

fun CharacterDto.toBo(): CharacterBo =
    CharacterBo(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.toBo(),
        location = location.toBo(),
        image = image,
        episode = episode.mapNotNull { Uri.parse(it).pathSegments.last()?.toInt() },
        url = url
    )

fun LocationSummaryDto.toBo(): LocationSummaryBo =
    LocationSummaryBo(name = name, code = url.getCodeFromUrl())

fun String.getCodeFromUrl(): Int =
    if (isEmpty()) {
        -1
    } else {
        Uri.parse(this).pathSegments.last()?.toInt() ?: -1
    }

fun LocationDto.toBo(): LocationBo =
    LocationBo(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
        residents = residents.mapNotNull { Uri.parse(it).pathSegments.last()?.toInt() },
        url = url,
    )

fun EpisodeDto.toBo(): EpisodeBo =
    EpisodeBo(
        id = id,
        name = name,
        airDate = air_date,
        episode = episode,
        characters = characters,
        url = url
    )