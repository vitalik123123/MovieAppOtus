package com.vitalik123.movieappotus.app

import android.app.Application
import com.vitalik123.core_api.CoreFacadeComponentProviders
import com.vitalik123.feature_home.di.DaggerFeatureHomeComponent
import com.vitalik123.feature_home.di.FeatureHomeComponent
import com.vitalik123.movieappotus.di.ApplicationComponent
import com.vitalik123.movieappotus.di.CoreFacadeComponent
import com.vitalik123.movieappotus.di.DaggerApplicationComponent

class App() : Application(), AppFacade, AppFeature {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.factory().create(this)
    }


    override val coreFacadeComponent: CoreFacadeComponentProviders
        get() = CoreFacadeComponent.init(this)

    override val featureHomeComponent: FeatureHomeComponent
        get() = DaggerFeatureHomeComponent.factory().create(coreFacadeComponent)

}