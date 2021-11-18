package com.palaspro.citiboxchallenge.datalayer.model

import com.palaspro.citiboxchallenge.datalayer.datasource.local.room.Character
import com.palaspro.citiboxchallenge.datalayer.di.URL_BASE

fun List<Character>.toDto(): ResponseInfoPaginationDto.Characters {
    val maxPage = maxOf { it.page }
    val hasMore = any { it.hasNextPage }
    val nextPage = if (hasMore) {
        "$URL_BASE/api/character?page=${maxPage + 1}"
    } else {
        null
    }
    val prevPage = if (maxPage > 1) {
        "$URL_BASE/api/character?page=${maxPage - 1}"
    } else {
        null
    }

    return ResponseInfoPaginationDto.Characters(
        info = InfoPaginationDto(count = size, pages = maxPage, next = nextPage, prev = prevPage),
        results = this.map { it.toDto() }
    )
}

fun Character.toDto(): CharacterDto =
    CharacterDto(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin,
        location = location,
        image = image,
        episode = episode,
        url = url,
        created = created,
    )