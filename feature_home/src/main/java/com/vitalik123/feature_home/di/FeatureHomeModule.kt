package com.vitalik123.feature_home.di

import androidx.lifecycle.ViewModel
import com.vitalik123.core.utils.ViewModelKey
import com.vitalik123.feature_home.view_model.FeatureHomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FeatureHomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(FeatureHomeViewModel::class)
    abstract fun bindMainViewModel(viewModel: FeatureHomeViewModel): ViewModel

}