package com.vitalik123.feature_details.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitalik123.core.utils.ViewModelKey
import com.vitalik123.feature_details.view_model.FeatureDetailsViewModel
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
    @ViewModelKey(FeatureDetailsViewModel::class)
    abstract fun bindsFeatureDetailsViewModel(viewModel: FeatureDetailsViewModel): ViewModel
}