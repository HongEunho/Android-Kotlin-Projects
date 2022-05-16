package com.example.meloncloneapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class PlayerFragment: Fragment(R.layout.fragment_player) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        // newInstance로 만드는 이유는 나중에 값을 넘겨줄 때 argument에 bundle을 넣어 넘겨주기 위해.
        fun newInstance(): PlayerFragment {
            return PlayerFragment()
        }
    }
}