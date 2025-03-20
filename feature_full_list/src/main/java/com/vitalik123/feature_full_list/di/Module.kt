package com.vitalik123.feature_full_list.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitalik123.core.utils.ViewModelKey
import com.vitalik123.feature_full_list.view_model.FeatureFullListViewModel
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
    @ViewModelKey(FeatureFullListViewModel::class)
    abstract fun bindsFeatureDetailsViewModel(viewModel: FeatureFullListViewModel): ViewModel
}