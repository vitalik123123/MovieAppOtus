package com.vitalik123.feature_home.di

import com.vitalik123.feature_home.repository.RepositoryHome
import com.vitalik123.feature_home.repository.RepositoryHomeImpl
import com.vitalik123.feature_home.use_case.UseCaseHome
import com.vitalik123.feature_home.use_case.UseCaseHomeImpl
import com.vitalik123.network.api.MovieApi
import dagger.Module
import dagger.Provides

@Module()
class RepositoryHomeModule {

    @Provides
    fun provideRepositoryHome(api: MovieApi): RepositoryHome =
        RepositoryHomeImpl(api)

    @Provides
    fun provideUseCaseHome(repository: RepositoryHome): UseCaseHome = UseCaseHomeImpl(repository)
}