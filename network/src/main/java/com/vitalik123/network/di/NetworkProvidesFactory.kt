package com.vitalik123.network.di

import com.vitalik123.core_api.CoreComponentProvider

object NetworkProvidesFactory {

    private var networkComponent: NetworkComponentProvider? = null

    fun getNetworkComponent(coreComponentProvider: CoreComponentProvider): NetworkComponentProvider =
        networkComponent ?: DaggerNetworkComponent.builder()
            .coreComponentProvider(coreComponentProvider).build().also {
            networkComponent = it
        }
}