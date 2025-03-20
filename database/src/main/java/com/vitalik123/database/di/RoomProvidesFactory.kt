package com.vitalik123.database.di

import com.vitalik123.core_api.CoreComponentProvider

object RoomProvidesFactory {

    private var roomComponent: RoomComponentProvider? = null

    fun getRoomComponent(coreComponentProvider: CoreComponentProvider): RoomComponentProvider =
        roomComponent ?: DaggerRoomComponent.builder().coreComponentProvider(coreComponentProvider)
            .build().also { roomComponent = it }
}