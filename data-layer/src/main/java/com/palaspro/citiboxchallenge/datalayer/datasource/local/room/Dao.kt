package com.palaspro.citiboxchallenge.datalayer.datasource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.palaspro.citiboxchallenge.datalayer.model.CharacterDto
import com.palaspro.citiboxchallenge.datalayer.model.LocationDto
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg characters: Character)

    @Query("DELETE FROM character")
    suspend fun deleteAll()

    @Query("SELECT * FROM character")
    fun getCharacters(): Flow<List<Character>>
}

@Dao
interface LocationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg locations: Location)

    @Query("DELETE FROM location")
    suspend fun deleteAll()

    @Query("SELECT * FROM location")
    suspend fun getLocation(): List<Location>
}

@Dao
interface EpisodesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg episode: Episode)

    @Query("DELETE FROM episode")
    suspend fun deleteAll()

    @Query("SELECT * FROM episode")
    suspend fun getEpisodes(): List<Episode>
}