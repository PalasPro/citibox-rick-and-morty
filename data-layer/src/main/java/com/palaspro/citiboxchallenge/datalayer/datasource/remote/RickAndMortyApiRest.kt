package com.palaspro.citiboxchallenge.datalayer.datasource.remote

import com.palaspro.citiboxchallenge.datalayer.model.CharacterDto
import com.palaspro.citiboxchallenge.datalayer.model.ResponseInfoPaginationDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiRest {

    @GET("/api/character")
    suspend fun getCharacters(@Query("page") page: Int = 1): Response<ResponseInfoPaginationDto.Characters>

    @GET("/api/character/{idCharacter}")
    suspend fun getCharacter(@Path("idCharacter") idCharacter: Int): Response<CharacterDto>
}