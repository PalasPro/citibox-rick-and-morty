package com.palaspro.citiboxchallenge.datalayer.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Character::class, Location::class, Episode::class], version = 1)
@TypeConverters(
    ConverterLocationSummary::class,
    ConverterListString::class,
    ConverterListInt::class
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
    abstract fun locationsDao(): LocationsDao
    abstract fun episodesDao(): EpisodesDao

}