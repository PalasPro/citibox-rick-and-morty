package com.palaspro.citiboxchallenge.datalayer.datasource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg characters: Character)

    @Query("SELECT * FROM character WHERE id = :id")
    suspend fun getCharacterSync(id: Int): Character?

    @Query("SELECT * FROM character WHERE page > 0")
    fun getCharacters(): Flow<List<Character>>

    @Query("SELECT * FROM character WHERE id = :id")
    fun getCharacter(id: Int): Flow<Character?>

}

@Dao
interface LocationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg locations: Location)

    @Query("SELECT * FROM location WHERE id = :code")
    suspend fun getLocation(code: Int): Location?
}

@Dao
interface EpisodesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg episode: Episode)

    @Query("SELECT * FROM episode WHERE id = :code")
    suspend fun getEpisode(code: Int): Episode?
}