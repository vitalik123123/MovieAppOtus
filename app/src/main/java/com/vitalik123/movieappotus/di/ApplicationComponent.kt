package com.vitalik123.movieappotus.di

import android.content.Context
import com.vitalik123.core_api.CoreProvider
import com.vitalik123.movieappotus.MainActivity
import dagger.BindsInstance
import dagger.Component


@Component()
interface ApplicationComponent : CoreProvider {

    fun inject(activity: MainActivity)

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