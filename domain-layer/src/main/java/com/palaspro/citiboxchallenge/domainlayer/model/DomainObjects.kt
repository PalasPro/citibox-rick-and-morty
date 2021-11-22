package com.palaspro.citiboxchallenge.domainlayer.model

sealed class ErrorBo {
    class ApiCall(val errorCode: Int) : ErrorBo()
    object NoData : ErrorBo()
    object UnKnown : ErrorBo()
}

sealed class ResponseInfoPaginationBo(
    val info: InfoPaginationBo
) {
    class Characters(info: InfoPaginationBo, val results: List<CharacterBo>) :
        ResponseInfoPaginationBo(info)
}

sealed class MatchStateBo {
    object Searching : MatchStateBo()
    object NotFound : MatchStateBo()
    data class MatchBeerBo(
        val characterBo: CharacterBo?,
        val locationBo: LocationBo?,
        val firstDate: String?,
        val lastDate: String?,
        val episodesMatch: Int = 0
    ) : MatchStateBo()
}


data class InfoPaginationBo(
    val count: Int,
    val pages: Int,
    val next: Int?,
    val prev: Int?
)

data class CharacterBo(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String?,
    val gender: String,
    val origin: LocationSummaryBo,
    val location: LocationSummaryBo,
    val image: String,
    val episode: List<Int>,
    val url: String
)

data class LocationSummaryBo(
    val name: String,
    val code: Int,
)

data class LocationBo(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<Int>,
    val url: String,
)

data class EpisodeBo(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
)