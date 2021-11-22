package com.palaspro.citiboxchallenge.datalayer.model

import com.palaspro.citiboxchallenge.datalayer.datasource.local.room.Character
import com.palaspro.citiboxchallenge.datalayer.datasource.local.room.Episode
import com.palaspro.citiboxchallenge.datalayer.datasource.local.room.Location

fun ResponseInfoPaginationDto.Characters.toEntity(page: Int): List<Character> {
    val hasNextPage = info.next != null

    return results.map {
        it.toEntity(page = page, hasNextPage = hasNextPage)
    }
}

fun CharacterDto.toEntity(page: Int = 0, hasNextPage: Boolean = false): Character =
    Character(
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
        page = page,
        hasNextPage = hasNextPage
    )

fun LocationDto.toEntity(): Location =
    Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
        residents = residents,
        url = url,
        created = created,
        page = 0,
        hasNextPage = false
    )

fun EpisodeDto.toEntity(): Episode =
    Episode(
        id = id,
        name = name,
        air_date = air_date,
        episode = episode,
        characters = characters,
        url = url,
        created = created,
        page = 0,
        hasNextPage = false
    )