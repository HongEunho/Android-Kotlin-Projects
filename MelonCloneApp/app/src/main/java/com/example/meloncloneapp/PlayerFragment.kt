package com.example.meloncloneapp

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.meloncloneapp.databinding.FragmentPlayerBinding
import com.example.meloncloneapp.service.MusicDto
import com.example.meloncloneapp.service.MusicService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlayerFragment: Fragment(R.layout.fragment_player) {

    private var binding: FragmentPlayerBinding? = null
    private var isWatchingPlayListView = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentPlayerBinding = FragmentPlayerBinding.bind(view)
        binding = fragmentPlayerBinding

        initPlayListButton(fragmentPlayerBinding)

        getVideoListFromServer()
    }

    private fun initPlayListButton(fragmentPlayerBinding: FragmentPlayerBinding) {
        fragmentPlayerBinding.playlistImageView.setOnClickListener {
            //todo 만약에 서버에서 데이터가 다 불려오지 않은 상태 일 때

            fragmentPlayerBinding.playerViewGroup.isVisible = isWatchingPlayListView
            fragmentPlayerBinding.playListViewGroup.isVisible = isWatchingPlayListView.not()

            isWatchingPlayListView = !isWatchingPlayListView
        }
    }

    private fun getVideoListFromServer() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MusicService::class.java)
            .also {
                it.listMusics()
                    .enqueue(object: Callback<MusicDto> {
                        override fun onResponse(call: Call<MusicDto>, response: Response<MusicDto>) {
                            response.body()?.let {
                                val modelList = it.musics.mapIndexed { index, musicEntity ->
                                    musicEntity.mapper(index.toLong())
                                }

                                
                            }
                        }

                        override fun onFailure(call: Call<MusicDto>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })
            }
    }

    companion object {
        // newInstance로 만드는 이유는 나중에 값을 넘겨줄 때 argument에 bundle을 넣어 넘겨주기 위해.
        fun newInstance(): PlayerFragment {
            return PlayerFragment()
        }
    }
}