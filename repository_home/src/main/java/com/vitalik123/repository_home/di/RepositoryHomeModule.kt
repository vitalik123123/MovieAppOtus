package com.vitalik123.repository_home.di

import com.vitalik123.core_api.annotation.FeatureHomeScope
import com.vitalik123.network.api.MovieApi
import com.vitalik123.repository_home.repository.RepositoryHome
import com.vitalik123.repository_home.repository.RepositoryHomeImpl
import com.vitalik123.repository_home.use_case.UseCaseHome
import com.vitalik123.repository_home.use_case.UseCaseHomeImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryHomeModule {

    @Provides
    @FeatureHomeScope
    fun provideRepositoryHome(api: MovieApi): RepositoryHome =
        RepositoryHomeImpl(api)

    @Provides
    @FeatureHomeScope
    fun provideUseCaseHome(repository: RepositoryHome): UseCaseHome = UseCaseHomeImpl(repository)
}