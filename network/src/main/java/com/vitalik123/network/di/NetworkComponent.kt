package com.vitalik123.network.di

import com.vitalik123.core_api.CoreComponentProvider
import dagger.Component

@Component(
    modules = [NetworkModule::class],
    dependencies = [CoreComponentProvider::class]
)
interface NetworkComponent {
}