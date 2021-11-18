package com.palaspro.citiboxchallenge.datalayer.datasource.local.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.palaspro.citiboxchallenge.datalayer.model.LocationSummaryDto

class ConverterLocationSummary {

    @TypeConverter
    fun fromString(location: String?): LocationSummaryDto? =
        location?.let {
            Gson().fromJson(location, LocationSummaryDto::class.java)
        }

    @TypeConverter
    fun toString(location: LocationSummaryDto?): String? =
        location?.let {
            Gson().toJson(location)
        }
}

class ConverterListString {

    @TypeConverter
    fun fromString(listString: String?): List<String>? =
        listString?.let {
            val listType = object : TypeToken<List<String>?>() {}.type
            Gson().fromJson(listString, listType)
        }

    @TypeConverter
    fun toString(list: List<String>?): String? =
        list?.let {
            Gson().toJson(list)
        }
}

class ConverterListInt {

    @TypeConverter
    fun fromString(listString: String?): List<Int>? =
        listString?.let {
            val listType = object : TypeToken<List<Int>?>() {}.type
            Gson().fromJson(listString, listType)
        }

    @TypeConverter
    fun toString(list: List<Int>?): String? =
        list?.let {
            Gson().toJson(list)
        }
}