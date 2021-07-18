package com.example.daggergallery.di

import com.example.daggergallery.GalleryActivity
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [GalleryModule::class])
interface GalleryComponent {
    fun inject(activity: GalleryActivity)

    fun getImageComponentFactory(): ImageComponent.Factory

    @Subcomponent.Factory
    interface Factory{
        fun create(@BindsInstance acctivity:GalleryActivity):GalleryComponent
    }
}