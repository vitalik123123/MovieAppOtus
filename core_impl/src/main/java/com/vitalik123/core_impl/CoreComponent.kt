package com.vitalik123.core_impl

import com.vitalik123.core_api.CoreComponentProvider
import com.vitalik123.core_api.CoreProvider
import dagger.Component

@Component(
    dependencies = [CoreProvider::class]
)
interface CoreComponent : CoreComponentProvider