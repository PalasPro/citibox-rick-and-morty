package com.palaspro.citiboxchallenge.datalayer.model

import com.palaspro.citiboxchallenge.datalayer.datasource.local.room.Character

fun ResponseInfoPaginationDto.Characters.toEntity(page: Int): List<Character> {
    val hasNextPage = info.next != null

    return results.map { dto ->
        Character(
            id = dto.id,
            name = dto.name,
            status = dto.status,
            species = dto.species,
            type = dto.type,
            gender = dto.gender,
            origin = dto.origin,
            location = dto.location,
            image = dto.image,
            episode = dto.episode,
            url = dto.url,
            created = dto.created,
            page = page,
            hasNextPage = hasNextPage
        )
    }
}