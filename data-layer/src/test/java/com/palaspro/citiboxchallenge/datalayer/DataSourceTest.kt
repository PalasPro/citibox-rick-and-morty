package com.palaspro.citiboxchallenge.datalayer

import arrow.core.Either
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import com.palaspro.citiboxchallenge.datalayer.datasource.local.room.Character
import com.palaspro.citiboxchallenge.datalayer.datasource.local.room.CharactersDao
import com.palaspro.citiboxchallenge.datalayer.datasource.remote.RickAndMortyApiRest
import com.palaspro.citiboxchallenge.datalayer.di.dataLayerModules
import com.palaspro.citiboxchallenge.datalayer.model.CharacterDto
import com.palaspro.citiboxchallenge.datalayer.model.ErrorDto
import com.palaspro.citiboxchallenge.datalayer.model.LocationSummaryDto
import com.palaspro.citiboxchallenge.domainlayer.model.CharacterBo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.mockito.Mockito.mock
import retrofit2.Response

@ExperimentalCoroutinesApi
class DataSourceTest : KoinTest {

    private val characterRemoteDataSource by inject<DataLayerContract.CharactersDataSource.Remote>()
    private val characterLocalDataSource by inject<DataLayerContract.CharactersDataSource.Local>()
    private lateinit var mockApiRest: RickAndMortyApiRest
    private lateinit var mockCharacterDao: CharactersDao

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        mockApiRest = mock(RickAndMortyApiRest::class.java)
        mockCharacterDao = mock(CharactersDao::class.java)

        printLogger()
        modules(
            dataLayerModules +
                    module {
                        single {
                            mockApiRest
                        }
                        single {
                            mockCharacterDao
                        }
                    }
        )
    }

    @After
    fun afterTest() {
        stopKoin()
    }

    @Test
    fun characterFromRemoteNotFound() {
        runBlockingTest {
            val wrongId = -1
            // given
            whenever(mockApiRest.getCharacter(wrongId)).doReturn(
                Response.error(
                    404,
                    "".toResponseBody()
                )
            )
            // when
            val result = characterRemoteDataSource.getCharacter(wrongId)
            // then
            Assert.assertTrue((result as? Either.Left<ErrorDto.NoData>) != null)
        }
    }

    @Test
    fun characterFromRemoteFound() {
        runBlockingTest {
            val correctId = 1
            // given
            whenever(mockApiRest.getCharacter(correctId)).doReturn(
                Response.success(buildCharacterDto())
            )
            // when
            val result = characterRemoteDataSource.getCharacter(correctId)
            // then
            Assert.assertTrue((result as? Either.Right<CharacterBo>) != null)
        }
    }

    @Test
    fun characterFromLocalNotFound() {
        runBlockingTest {
            val wrongId = -1
            // given
            whenever(mockCharacterDao.getCharacterSync(wrongId)).doReturn(null)
            // when
            val result = characterLocalDataSource.getCharacterSync(wrongId)
            // then
            Assert.assertTrue((result as? Either.Left<ErrorDto.NoData>) != null)
        }
    }

    @Test
    fun characterFromLocalFound() {
        runBlockingTest {
            val correctId = 1
            // given
            whenever(mockCharacterDao.getCharacterSync(correctId)).doReturn(buildCharacterEntity())
            // when
            val result = characterLocalDataSource.getCharacterSync(correctId)
            // then
            Assert.assertTrue((result as? Either.Right<CharacterBo>) != null)
        }
    }

    private fun buildCharacterDto() =
        CharacterDto(
            id = 1,
            name = "Rick Sanchez",
            species = "Human",
            type = "Rare",
            gender = "Male",
            origin = buildLocation(),
            status = "Alive",
            location = buildLocation(),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf(
                "https://rickandmortyapi.com/api/episode/1",
                "https://rickandmortyapi.com/api/episode/2"
            ),
            url = "https://rickandmortyapi.com/api/character/1",
            created = "2017-11-04T18:48:46.250Z"
        )

    private fun buildCharacterEntity() =
        Character(
            id = 1,
            name = "Rick Sanchez",
            species = "Human",
            type = "Rare",
            gender = "Male",
            origin = buildLocation(),
            status = "Alive",
            location = buildLocation(),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf(
                "https://rickandmortyapi.com/api/episode/1",
                "https://rickandmortyapi.com/api/episode/2"
            ),
            url = "https://rickandmortyapi.com/api/character/1",
            created = "2017-11-04T18:48:46.250Z",
            page = 1,
            hasNextPage = true
        )

    private fun buildLocation() =
        LocationSummaryDto(
            name = "Earth",
            url = "https://rickandmortyapi.com/api/location/20"
        )
}