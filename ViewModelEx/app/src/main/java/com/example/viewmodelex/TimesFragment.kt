package com.example.viewmodelex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelex.databinding.FragmentTimesBinding


class TimesFragment : Fragment() {
    private lateinit var binding: FragmentTimesBinding
    private val viewModel: MainViewModel by lazy{
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTimesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.timesText.observe(viewLifecycleOwner) {
            binding.timesText.text = it
        }
    }

}