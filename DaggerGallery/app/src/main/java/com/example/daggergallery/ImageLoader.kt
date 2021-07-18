package com.example.daggergallery

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import com.example.daggergallery.di.FragmentScope
import javax.inject.Inject

@FragmentScope
class ImageLoader @Inject constructor(private val context: Context){

    fun load(imageView: ImageView, uri: Uri) {
        val bm = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, uri))
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }
        imageView.setImageBitmap(bm)
    }
}