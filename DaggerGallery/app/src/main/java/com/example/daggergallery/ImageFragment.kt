package com.example.daggergallery

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.daggergallery.databinding.FragmentImageBinding
import javax.inject.Inject

class ImageFragment : Fragment() {

    companion object {
        // ...
    }

    @Inject
    lateinit var viewModel: GalleryViewModel

    @Inject
    lateinit var imageLoader: ImageLoader

    private var _binding: FragmentImageBinding? = null
    private val binding: FragmentImageBinding get() = _binding!!

    override fun onAttach(context: Context) {
        (activity as GalleryActivity).component
            .getImageComponentFactory()
            .create()
            .inject(this)

        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }
}