package com.palaspro.citiboxchallenge.datalayer.di

import androidx.room.Room
import com.palaspro.citiboxchallenge.datalayer.DataLayerContract
import com.palaspro.citiboxchallenge.datalayer.datasource.local.CharactersLocalDataSourceImpl
import com.palaspro.citiboxchallenge.datalayer.datasource.local.EpisodeLocalDataSourceImpl
import com.palaspro.citiboxchallenge.datalayer.datasource.local.LocationLocalDataSourceImpl
import com.palaspro.citiboxchallenge.datalayer.datasource.local.room.AppDataBase
import com.palaspro.citiboxchallenge.datalayer.datasource.remote.CharactersRemoteDataSourceImpl
import com.palaspro.citiboxchallenge.datalayer.datasource.remote.EpisodeRemoteDataSourceImpl
import com.palaspro.citiboxchallenge.datalayer.datasource.remote.LocationRemoteDataSourceImpl
import com.palaspro.citiboxchallenge.datalayer.datasource.remote.RickAndMortyApiRest
import com.palaspro.citiboxchallenge.datalayer.repository.CharactersRepositoryImpl
import com.palaspro.citiboxchallenge.datalayer.repository.EpisodeRepositoryImpl
import com.palaspro.citiboxchallenge.datalayer.repository.LocationRepositoryImpl
import com.palaspro.citiboxchallenge.domainlayer.DomainLayerContract
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val URL_BASE = "https://rickandmortyapi.com"
private const val DATABASE_NAME = "rick-and-morty-db"

private val dataSourceModules = module {

    single<RickAndMortyApiRest> {
        val logInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val httpClient = OkHttpClient.Builder().addInterceptor(logInterceptor).build()

        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApiRest::class.java)
    }

    single {
        Room.databaseBuilder(androidContext(), AppDataBase::class.java, DATABASE_NAME).build()
    }

    single {
        get<AppDataBase>().charactersDao()
    }

    single {
        get<AppDataBase>().locationsDao()
    }

    single {
        get<AppDataBase>().episodesDao()
    }

    single<DataLayerContract.CharactersDataSource.Remote> {
        CharactersRemoteDataSourceImpl(get())
    }

    single<DataLayerContract.CharactersDataSource.Local> {
        CharactersLocalDataSourceImpl(get())
    }

    single<DataLayerContract.LocationsDataSource.Remote> {
        LocationRemoteDataSourceImpl(get())
    }

    single<DataLayerContract.LocationsDataSource.Local> {
        LocationLocalDataSourceImpl(get())
    }

    single<DataLayerContract.EpisodesDataSource.Remote> {
        EpisodeRemoteDataSourceImpl(get())
    }

    single<DataLayerContract.EpisodesDataSource.Local> {
        EpisodeLocalDataSourceImpl(get())
    }
}
private val repositoryModules = module {

    factory<DomainLayerContract.CharactersRepository> {
        CharactersRepositoryImpl(
            remote = get(),
            local = get()
        )
    }

    factory<DomainLayerContract.LocationRepository> {
        LocationRepositoryImpl(
            remote = get(),
            local = get()
        )
    }

    factory<DomainLayerContract.EpisodeRepository> {
        EpisodeRepositoryImpl(
            remote = get(),
            local = get()
        )
    }
}

val dataLayerModules = listOf(dataSourceModules, repositoryModules)