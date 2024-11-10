package com.mkao.skysync.presentation.di

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object LocationModule {
    @Provides
    fun provideLocationProvider(
        @ActivityContext context: Context
    ): FusedLocationProviderClient{
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    fun providesGeoCoder(
        @ActivityContext context: Context
    ): Geocoder{
        return Geocoder(context)
    }
}