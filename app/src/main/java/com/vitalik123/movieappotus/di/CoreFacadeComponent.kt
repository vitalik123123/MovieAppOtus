package com.vitalik123.movieappotus.di

import android.content.Context
import android.util.Log
import com.vitalik123.core.di.CoreProvidesFactory
import com.vitalik123.core_api.CoreComponentProvider
import com.vitalik123.core_api.CoreFacadeComponentProviders
import com.vitalik123.movieappotus.MainActivity
import dagger.Component

@Component(
    dependencies = [CoreComponentProvider::class]
)
interface CoreFacadeComponent: CoreFacadeComponentProviders  {

    companion object {

        fun init(context: Context): CoreFacadeComponent {
            val coreContextProvider =
                ApplicationComponent.getApplicationComponent(context)
            val coreComponentProvider =
                CoreProvidesFactory.getCoreComponent(coreContextProvider)

            return DaggerCoreFacadeComponent
                .builder()
                .coreComponentProvider(coreComponentProvider)
                .build()
        }
    }
}