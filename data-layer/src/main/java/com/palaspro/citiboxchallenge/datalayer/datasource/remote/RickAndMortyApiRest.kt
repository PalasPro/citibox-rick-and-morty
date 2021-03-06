package com.palaspro.citiboxchallenge.datalayer.datasource.remote

import com.palaspro.citiboxchallenge.datalayer.model.CharacterDto
import com.palaspro.citiboxchallenge.datalayer.model.EpisodeDto
import com.palaspro.citiboxchallenge.datalayer.model.LocationDto
import com.palaspro.citiboxchallenge.datalayer.model.ResponseInfoPaginationDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RickAndMortyApiRest {

    @GET("/api/character")
    suspend fun getCharacters(@QueryMap query: Map<String, String>): Response<ResponseInfoPaginationDto.Characters>

    @GET("/api/character/{idCharacter}")
    suspend fun getCharacter(@Path("idCharacter") idCharacter: Int): Response<CharacterDto>

    @GET("/api/location/{idLocation}")
    suspend fun getLocation(@Path("idLocation") idLocation: Int): Response<LocationDto>

    @GET("/api/episode/{idEpisode}")
    suspend fun getEpisode(@Path("idEpisode") idEpisode: Int): Response<EpisodeDto>
}