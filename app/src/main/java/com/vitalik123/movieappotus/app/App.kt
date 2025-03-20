package com.vitalik123.movieappotus.app

import android.app.Application
import com.vitalik123.core_api.CoreFacadeComponentProviders
import com.vitalik123.database.di.RoomFacadeComponentProviders
import com.vitalik123.movieappotus.di.CoreFacadeComponent
import com.vitalik123.movieappotus.di.NetworkFacadeComponent
import com.vitalik123.movieappotus.di.RoomFacadeComponent
import com.vitalik123.network.di.NetworkFacadeComponentProviders

class App() : Application(), AppFacade {

    override val coreFacadeComponent: CoreFacadeComponentProviders by lazy {
        CoreFacadeComponent.init(this)
    }

    override val networkFacadeComponent: NetworkFacadeComponentProviders by lazy {
        NetworkFacadeComponent.init(this)
    }

    override val roomFacadeComponent: RoomFacadeComponentProviders by lazy {
        RoomFacadeComponent.init(this)
    }

}