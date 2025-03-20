package com.vitalik123.feature_home.di

import com.vitalik123.core_api.CoreFacadeComponentProviders
import com.vitalik123.network.di.NetworkFacadeComponentProviders
import com.vitalik123.network.di.NetworkModule
import dagger.Component

@Component(
    modules = [NetworkModule::class, RepositoryHomeModule::class, ViewModelModule::class, ViewModelFactoryModule::class],
    dependencies = [CoreFacadeComponentProviders::class, NetworkFacadeComponentProviders::class]
)
interface FeatureHomeComponent {
    fun getViewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {

        fun create(
            coreFacadeComponentProviders: CoreFacadeComponentProviders,
            networkFacadeComponentProviders: NetworkFacadeComponentProviders,
        ): FeatureHomeComponent
    }
}