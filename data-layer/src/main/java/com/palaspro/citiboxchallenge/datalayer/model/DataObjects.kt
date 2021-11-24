package com.palaspro.citiboxchallenge.datalayer.model


sealed class ErrorDto {
    class ApiCall(val errorCode: Int) : ErrorDto()
    object FatalApiCall : ErrorDto()
    object NoData : ErrorDto()
    object UnKnown : ErrorDto()
}

sealed class ResponseInfoPaginationDto(
    val info: InfoPaginationDto
) {
    class Characters(info: InfoPaginationDto, val results: List<CharacterDto>) :
        ResponseInfoPaginationDto(info)
}

data class InfoPaginationDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class CharacterDto(
    val id: Int,
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
    val created: String
)

data class LocationSummaryDto(
    val name: String,
    val url: String,
)

data class LocationDto(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String,
)

data class EpisodeDto(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)

data class RequestConfigDto(
    var page : Int = 1,
    var query: String? = null
)