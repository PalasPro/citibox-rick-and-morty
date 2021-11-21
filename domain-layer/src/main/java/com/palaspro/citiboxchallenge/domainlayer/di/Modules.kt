package com.palaspro.citiboxchallenge.domainlayer.di

import arrow.core.Either
import com.palaspro.citiboxchallenge.domainlayer.DomainLayerContract
import com.palaspro.citiboxchallenge.domainlayer.bridge.ListCharactersBridge
import com.palaspro.citiboxchallenge.domainlayer.bridge.ListCharactersBridgeImpl
import com.palaspro.citiboxchallenge.domainlayer.model.ErrorBo
import com.palaspro.citiboxchallenge.domainlayer.model.ResponseInfoPaginationBo
import com.palaspro.citiboxchallenge.domainlayer.usecase.GET_LIST_CHARACTERS_UC
import com.palaspro.citiboxchallenge.domainlayer.usecase.GetListCharactersUc
import com.palaspro.citiboxchallenge.domainlayer.usecase.LOAD_PAGE_CHARACTERS_UC
import com.palaspro.citiboxchallenge.domainlayer.usecase.LoadPageCharactersUc
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val bridgeModules = module {

    factory<ListCharactersBridge> {
        ListCharactersBridgeImpl(
            getListCharactersUc = get(named(name = GET_LIST_CHARACTERS_UC)),
            loadPageCharactersUc = get(named(name = LOAD_PAGE_CHARACTERS_UC))
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

    factory<DomainLayerContract.UseCase<Int, Either<ErrorBo, Boolean>>>(named(name = LOAD_PAGE_CHARACTERS_UC)) {
        LoadPageCharactersUc(
            repository = get()
        )
    }
}

val domainLayerModules = listOf(useCaseModules, bridgeModules)