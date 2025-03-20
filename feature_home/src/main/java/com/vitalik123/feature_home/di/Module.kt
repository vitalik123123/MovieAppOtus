package com.vitalik123.feature_home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitalik123.core.utils.ViewModelKey
import com.vitalik123.core_api.annotation.FeatureHomeScope
import com.vitalik123.feature_home.view_model.FeatureHomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FeatureHomeViewModel::class)
    abstract fun bindsFeatureHomeViewModel(viewModel: FeatureHomeViewModel): ViewModel
}