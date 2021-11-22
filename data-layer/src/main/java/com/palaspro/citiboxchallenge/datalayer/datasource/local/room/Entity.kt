package com.palaspro.citiboxchallenge.datalayer.datasource.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.palaspro.citiboxchallenge.datalayer.model.LocationSummaryDto

@Entity(tableName = "character")
data class Character(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String?,
    val gender: String,
    val origin: LocationSummaryDto,
    val location: LocationSummaryDto,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
    val page: Int,
    val hasNextPage: Boolean
)

@Entity(tableName = "location")
data class Location(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String,
    val page: Int,
    val hasNextPage: Boolean
)

@Entity(tableName = "episode")
data class Episode(
    @PrimaryKey val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String,
    val page: Int,
    val hasNextPage: Boolean
)