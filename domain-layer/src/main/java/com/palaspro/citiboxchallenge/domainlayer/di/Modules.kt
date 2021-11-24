package com.palaspro.citiboxchallenge.domainlayer.di

import arrow.core.Either
import com.palaspro.citiboxchallenge.domainlayer.DomainLayerContract
import com.palaspro.citiboxchallenge.domainlayer.bridge.ListCharactersBridge
import com.palaspro.citiboxchallenge.domainlayer.bridge.ListCharactersBridgeImpl
import com.palaspro.citiboxchallenge.domainlayer.bridge.MatchCharacterBridge
import com.palaspro.citiboxchallenge.domainlayer.bridge.MatchCharacterBridgeImpl
import com.palaspro.citiboxchallenge.domainlayer.model.CharacterBo
import com.palaspro.citiboxchallenge.domainlayer.model.ErrorBo
import com.palaspro.citiboxchallenge.domainlayer.model.MatchStateBo
import com.palaspro.citiboxchallenge.domainlayer.model.ResponseInfoPaginationBo
import com.palaspro.citiboxchallenge.domainlayer.usecase.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val bridgeModules = module {

    factory<ListCharactersBridge> {
        ListCharactersBridgeImpl(
            getListCharactersUc = get(named(name = GET_LIST_CHARACTERS_UC)),
            loadPageCharactersUc = get(named(name = LOAD_PAGE_CHARACTERS_UC)),
            filterCharactersUc = get(named(name = FILTER_CHARACTERS_UC))
        )
    }

    factory<MatchCharacterBridge> {
        MatchCharacterBridgeImpl(
            getCharacterUc = get(named(name = GET_CHARACTER_UC)),
            matchBeerUc = get(named(name = BEST_MATCH_BEER_UC))
        )
    }
}

private val useCaseModules = module {

    factory<DomainLayerContract.UseCaseFlow<Any?, Either<ErrorBo, ResponseInfoPaginationBo.Characters>>>(
        named(name = GET_LIST_CHARACTERS_UC)
    ) {
        GetListCharactersUc(
            repository = get()
        )
    }

    factory<DomainLayerContract.UseCase<Any?, Either<ErrorBo, Boolean>>>(named(name = LOAD_PAGE_CHARACTERS_UC)) {
        LoadPageCharactersUc(
            repository = get()
        )
    }

    factory<DomainLayerContract.UseCase<String, Either<ErrorBo, Boolean>>>(named(name = FILTER_CHARACTERS_UC)) {
        FilterCharactersUc(
            repository = get()
        )
    }

    factory<DomainLayerContract.UseCaseFlow<Int, Either<ErrorBo, CharacterBo>>>(named(name = GET_CHARACTER_UC)) {
        GetCharacterUc(
            repository = get()
        )
    }

    factory<DomainLayerContract.UseCaseFlow<Int, MatchStateBo>>(named(name = BEST_MATCH_BEER_UC)) {
        BestMatchBeerUc(
            repositoryCharacter = get(),
            locationRepository = get(),
            episodeRepository = get()
        )
    }
}

val domainLayerModules = listOf(useCaseModules, bridgeModules)