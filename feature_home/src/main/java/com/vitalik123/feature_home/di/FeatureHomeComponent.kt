package com.vitalik123.feature_home.di

import com.vitalik123.core_api.CoreFacadeComponentProviders
import com.vitalik123.core_api.annotation.ApplicationScope
import com.vitalik123.core_api.annotation.FeatureHomeScope
import com.vitalik123.feature_home.view_model.FeatureHomeViewModel
import com.vitalik123.repository_home.di.RepositoryHomeModule
import dagger.Component

@Component(
    modules = [RepositoryHomeModule::class, FeatureHomeModule::class],
    dependencies = [CoreFacadeComponentProviders::class]
)
interface FeatureHomeComponent{

    @Component.Factory
    interface Factory {

        fun create(
            coreFacadeComponentProviders: CoreFacadeComponentProviders,
        ): FeatureHomeComponent
    }
}