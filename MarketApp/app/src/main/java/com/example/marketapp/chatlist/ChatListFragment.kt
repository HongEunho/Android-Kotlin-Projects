package com.example.marketapp.chatlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marketapp.R


class ChatListFragment : Fragment(R.layout.fragment_chat_list) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("onCreate", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("onCreateView", "onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_list, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("ChatListFragment", "destroyview")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("Detach", "detach")
    }
}