package com.vitalik123.core.di

import com.vitalik123.core_api.CoreComponentProvider
import com.vitalik123.core_api.CoreProvider
import com.vitalik123.core_impl.DaggerCoreComponent

object CoreProvidesFactory {

    private var coreComponent: CoreComponentProvider? = null

    fun getCoreComponent(coreProvider: CoreProvider): CoreComponentProvider =
        coreComponent ?: DaggerCoreComponent.builder().coreProvider(coreProvider).build().also {
            coreComponent = it
        }
}