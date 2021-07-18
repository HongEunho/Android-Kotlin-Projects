package com.example.daggergallery

import android.net.Uri
import com.example.daggergallery.di.ActivityScope
import javax.inject.Inject

@ActivityScope
class GalleryViewModel @Inject constructor(private val repository: ImageRepository){
    val items: List<Uri> by lazy {
        repository.getImages()
    }
}