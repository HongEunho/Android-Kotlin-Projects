package com.example.daggergallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.daggergallery.di.ApplicationComponent
import com.example.daggergallery.di.GalleryComponent
import java.util.jar.Manifest
import javax.inject.Inject

class GalleryActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: GalleryViewModel

    private val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        // ...
    }

    lateinit var component: GalleryComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        component = MyApplication.appComponent.getGalleryComponentFactory().create(this)
        component.inject(this)

        super.onCreate(savedInstanceState)

        launcher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        setContentView(R.layout.activity_gallery)
    }

    private fun init() {
        // ...
    }
}