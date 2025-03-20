package com.vitalik123.feature_search.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitalik123.core.utils.ViewModelKey
import com.vitalik123.feature_search.view_model.FeatureSearchViewModel
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
    @ViewModelKey(FeatureSearchViewModel::class)
    abstract fun bindsFeatureDetailsViewModel(viewModel: FeatureSearchViewModel): ViewModel
}