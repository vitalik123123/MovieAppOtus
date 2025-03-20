package com.vitalik123.movieappotus.di

import android.content.Context
import com.vitalik123.core.di.CoreProvidesFactory
import com.vitalik123.database.di.RoomComponentProvider
import com.vitalik123.database.di.RoomFacadeComponentProviders
import com.vitalik123.database.di.RoomProvidesFactory
import dagger.Component

@Component(
    dependencies = [RoomComponentProvider::class]
)
interface RoomFacadeComponent : RoomFacadeComponentProviders{

    companion object {
        fun init(context: Context): RoomFacadeComponent {
            val coreContextProvider =
                ApplicationComponent.getApplicationComponent(context)
            val coreComponentProvider =
                CoreProvidesFactory.getCoreComponent(coreContextProvider)
            val roomComponentProvider =
                RoomProvidesFactory.getRoomComponent(coreComponentProvider)

            return DaggerRoomFacadeComponent.builder().roomComponentProvider(roomComponentProvider).build()
        }
    }
}