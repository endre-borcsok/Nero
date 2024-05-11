package com.ebsoftware.nero.core.common.io

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val neroDispatcher: NeroDispatchers)

enum class NeroDispatchers {
    Default,
    IO,
}
