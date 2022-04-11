package com.example.youtubecloneapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.youtubecloneapp.databinding.FragmentPlayerBinding

class PlayerFragment: Fragment(R.layout.fragment_player) {

    private var binding : FragmentPlayerBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentPlayerBinding = FragmentPlayerBinding.bind(view)
        binding = fragmentPlayerBinding
    }
}