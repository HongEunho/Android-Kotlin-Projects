package com.example.daggergallery.di

import android.app.Application
import com.example.daggergallery.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class])
interface ApplicationComponent: AndroidInjector<MyApplication>{

    @Component.Factory
    interface Factory: AndroidInjector.Factory<MyApplication> {
        // fun create(@BindsInstance application: Application): ApplicationComponent
    }
    fun getGalleryComponentFactory(): GalleryComponent.Factory
}