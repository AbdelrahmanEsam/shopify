package com.example.shopify.utils.hiltanotations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: Dispatchers)

enum class Dispatchers {
    Default,
    IO,
    Main,
    Unconfined
}