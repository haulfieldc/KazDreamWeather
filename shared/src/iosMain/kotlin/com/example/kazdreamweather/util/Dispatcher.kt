package com.example.kazdreamweather.util

import kotlinx.coroutines.CoroutineDispatcher

internal class IosDispatcher: Dispatcher{
    override val io: CoroutineDispatcher
        get() = Dispatchers.Default
}

internal actual fun provideDispatcher(): Dispatcher = IosDispatcher()