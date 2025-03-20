package com.vitalik123.movieappotus.di

import android.content.Context
import com.vitalik123.core.di.CoreProvidesFactory
import com.vitalik123.network.di.NetworkFacadeComponentProviders
import com.vitalik123.network.di.NetworkComponentProvider
import com.vitalik123.network.di.NetworkProvidesFactory
import dagger.Component

@Component(
    dependencies = [NetworkComponentProvider::class]
)
interface NetworkFacadeComponent : NetworkFacadeComponentProviders {

    companion object {
        fun init(context: Context): NetworkFacadeComponent {
            val coreContextProvider =
                ApplicationComponent.getApplicationComponent(context)
            val coreComponentProvider
            = CoreProvidesFactory.getCoreComponent(coreContextProvider)
            val networkComponentProvider =
                NetworkProvidesFactory.getNetworkComponent(coreComponentProvider)

            return DaggerNetworkFacadeComponent.builder().networkComponentProvider(networkComponentProvider).build()
        }
    }
}