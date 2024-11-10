package com.mkao.skysync.domain.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Dispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher
