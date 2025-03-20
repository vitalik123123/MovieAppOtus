package com.vitalik123.feature_details.di

import com.vitalik123.feature_details.repository.RepositoryDetails
import com.vitalik123.feature_details.repository.RepositoryDetailsImpl
import com.vitalik123.feature_details.use_case.UseCaseDetails
import com.vitalik123.feature_details.use_case.UseCaseDetailsImpl
import com.vitalik123.network.api.MovieApi
import dagger.Module
import dagger.Provides

@Module
class RepositoryDetailsModule {

    @Provides
    fun provideRepositoryDetails(api: MovieApi): RepositoryDetails =
        RepositoryDetailsImpl(api)

    @Provides
    fun provideUseCaseDetails(repository: RepositoryDetails): UseCaseDetails =
        UseCaseDetailsImpl(repository)
}