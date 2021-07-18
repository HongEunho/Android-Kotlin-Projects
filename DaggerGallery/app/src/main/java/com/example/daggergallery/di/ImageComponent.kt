package com.example.daggergallery.di

import androidx.fragment.app.Fragment
import com.example.daggergallery.ImageFragment
import dagger.Subcomponent

@Subcomponent(modules = [ImageModule::class])
interface ImageComponent {

    fun inject(fragment: ImageFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create():ImageComponent
    }
}