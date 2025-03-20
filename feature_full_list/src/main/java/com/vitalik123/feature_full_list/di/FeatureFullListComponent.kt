package com.vitalik123.feature_full_list.di

import com.vitalik123.core_api.CoreFacadeComponentProviders
import com.vitalik123.database.di.RoomFacadeComponentProviders
import com.vitalik123.database.di.RoomModule
import com.vitalik123.network.di.NetworkFacadeComponentProviders
import com.vitalik123.network.di.NetworkModule
import dagger.Component

@Component(
    modules = [NetworkModule::class, RoomModule::class,ViewModelModule::class,ViewModelFactoryModule::class],
    dependencies = [CoreFacadeComponentProviders::class, NetworkFacadeComponentProviders::class, RoomFacadeComponentProviders::class]
)
interface FeatureFullListComponent {
    fun getViewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(
            coreFacadeComponentProviders: CoreFacadeComponentProviders,
            networkFacadeComponentProviders: NetworkFacadeComponentProviders,
            roomFacadeComponentProviders: RoomFacadeComponentProviders
        ): FeatureFullListComponent
    }
}