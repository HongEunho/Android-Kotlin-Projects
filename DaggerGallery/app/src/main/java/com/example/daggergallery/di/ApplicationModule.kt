package com.example.daggergallery.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module(subcomponents = [GalleryComponent::class])
abstract class ApplicationModule {

    @Binds
    abstract fun bindsContext(application: Application): Context
}