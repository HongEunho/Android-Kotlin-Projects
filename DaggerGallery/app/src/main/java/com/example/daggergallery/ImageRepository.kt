package com.example.daggergallery

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.example.daggergallery.di.ActivityScope
import javax.inject.Inject

@ActivityScope
class ImageRepository @Inject constructor(private val context: Context) {

    fun getImages(): List<Uri> {
        val images = ArrayList<Uri>()
        val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val cursor: Cursor? = context.contentResolver.query(
            contentUri,
            null,
            null,
            null,
            "${MediaStore.Images.ImageColumns.DATE_ADDED} DESC"
        )
        cursor?.let { c->
            while(c.moveToNext()){
                val id = c.getLong(c.getColumnIndex(MediaStore.Images.ImageColumns._ID))
                images.add(ContentUris.withAppendedId(contentUri, id))
            }
        }
        return images
    }
}