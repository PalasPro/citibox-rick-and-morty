package com.palaspro.citiboxchallenge.datalayer

import arrow.core.left
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import com.palaspro.citiboxchallenge.domainlayer.DomainLayerContract
import com.palaspro.citiboxchallenge.domainlayer.di.domainLayerModules
import com.palaspro.citiboxchallenge.domainlayer.model.ErrorBo
import com.palaspro.citiboxchallenge.domainlayer.model.MatchStateBo
import com.palaspro.citiboxchallenge.domainlayer.usecase.BEST_MATCH_BEER_UC
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class UseCasesTest : KoinTest {

    private val matchBeerUc by inject<DomainLayerContract.UseCaseFlow<Int, MatchStateBo>>(named(name = BEST_MATCH_BEER_UC))
    private lateinit var mockCharacterRepository: DomainLayerContract.CharactersRepository
    private lateinit var mockLocationRepository: DomainLayerContract.LocationRepository
    private lateinit var mockEpisodeRepository: DomainLayerContract.EpisodeRepository

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        mockCharacterRepository = mock(DomainLayerContract.CharactersRepository::class.java)
        mockLocationRepository = mock(DomainLayerContract.LocationRepository::class.java)
        mockEpisodeRepository = mock(DomainLayerContract.EpisodeRepository::class.java)

        printLogger()
        modules(
            domainLayerModules +
                    module {
                        single {
                            mockCharacterRepository
                        }
                        single {
                            mockLocationRepository
                        }
                        single {
                            mockEpisodeRepository
                        }
                    }
        )
    }

    @After
    fun afterTest() {
        stopKoin()
    }

    @Test
    fun matchBeerUcFirstStateIsSearching() {
        runBlockingTest {
            val wrongId = -1
            // given
            whenever(mockCharacterRepository.getCharacterSync(wrongId)).doReturn(ErrorBo.NoData.left())
            // when
            val result = matchBeerUc.run(wrongId).first()
            // then
            Assert.assertTrue(result is MatchStateBo.Searching)
        }
    }

    @Test
    fun matchBeerUcWrongIdLastStateIsNotFound() {
        runBlockingTest {
            val wrongId = -1
            // given
            whenever(mockCharacterRepository.getCharacterSync(wrongId)).doReturn(ErrorBo.NoData.left())
            // when
            val result = matchBeerUc.run(wrongId).last()
            // then
            Assert.assertTrue(result is MatchStateBo.NotFound)
        }
    }
}