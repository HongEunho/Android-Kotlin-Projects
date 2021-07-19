package com.example.daggergallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.daggergallery.di.ApplicationComponent
import com.example.daggergallery.di.GalleryComponent
import dagger.android.support.DaggerAppCompatActivity
import java.util.jar.Manifest
import javax.inject.Inject

class GalleryActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: GalleryViewModel

    private val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) { init() } else { finish() }
    }

    lateinit var component: GalleryComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        /* AndroidInject를 사용했기 때문에 서브 컴포넌트 생성 및 인젝션 하는 코드를 작성하지 않아도 됨
        component = MyApplication.appComponent.getGalleryComponentFactory().create(this)
        component.inject(this)*/

        super.onCreate(savedInstanceState)
        launcher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        setContentView(R.layout.activity_gallery)
    }

    private fun init() {
        // ...
    }
}