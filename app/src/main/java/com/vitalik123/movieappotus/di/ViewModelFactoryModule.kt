package com.vitalik123.movieappotus.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitalik123.core.di.ViewModelFactory
import com.vitalik123.movieappotus.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
object ViewModelFactoryModule {

    @JvmStatic
    @Provides
    fun provideViewModelFactory(
        viewModels: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
    ): ViewModelProvider.Factory {
        return ViewModelFactory(viewModels)
    }
}
