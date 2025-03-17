package com.vitalik123.movieappotus.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitalik123.core.di.ViewModelFactory
import com.vitalik123.core_api.CoreProvider
import com.vitalik123.core_api.annotation.ApplicationScope
import com.vitalik123.movieappotus.MainActivity
import dagger.Binds
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [ ViewModelFactoryModule::class]
)
interface ApplicationComponent : CoreProvider {

    fun inject(activity: MainActivity)

//    fun viewModelFactory(): ViewModelFactory

    companion object {

        private var applicationComponent: ApplicationComponent? = null

        fun getApplicationComponent(context: Context): CoreProvider =
            applicationComponent ?: DaggerApplicationComponent
                .factory()
                .create(context)
                .also {
                    applicationComponent = it
                }
    }

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}