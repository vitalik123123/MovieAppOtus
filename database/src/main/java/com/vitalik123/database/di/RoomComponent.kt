package com.vitalik123.database.di

import com.vitalik123.core_api.CoreComponentProvider
import dagger.Component

@Component(
    modules = [RoomModule::class],
    dependencies = [CoreComponentProvider::class]
)
interface RoomComponent : RoomComponentProvider