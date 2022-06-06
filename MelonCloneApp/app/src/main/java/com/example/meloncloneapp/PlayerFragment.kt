package com.example.meloncloneapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meloncloneapp.databinding.FragmentPlayerBinding
import com.example.meloncloneapp.service.MusicDto
import com.example.meloncloneapp.service.MusicService
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlayerFragment: Fragment(R.layout.fragment_player) {

    private var model: PlayerModel = PlayerModel()
    private var binding: FragmentPlayerBinding? = null
    private var player: SimpleExoPlayer? = null
    private lateinit var playListAdapter: PlayListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentPlayerBinding = FragmentPlayerBinding.bind(view)
        binding = fragmentPlayerBinding

        initPlayView(fragmentPlayerBinding)
        initPlayListButton(fragmentPlayerBinding)
        initPlayControlButtons(fragmentPlayerBinding)
        initRecyclerView(fragmentPlayerBinding)

        getVideoListFromServer()
    }

    private fun initPlayControlButtons(fragmentPlayerBinding: FragmentPlayerBinding) {
        fragmentPlayerBinding.playControlImageView.setOnClickListener {
            val player = this.player ?: return@setOnClickListener

            if (player.isPlaying) {
                player.pause()
            } else {
                player.play()
            }

        }

        fragmentPlayerBinding.skipNextImageView.setOnClickListener {

        }

        fragmentPlayerBinding.skipPrevImageView.setOnClickListener {

        }
    }

    private fun initPlayView(fragmentPlayerBinding: FragmentPlayerBinding){
        player = context?.let { SimpleExoPlayer.Builder(it).build() }

        fragmentPlayerBinding.playerView.player = player

        binding?.let {
            player?.addListener(object : Player.Listener {
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    super.onIsPlayingChanged(isPlaying)

                    if (isPlaying) {
                        it.playControlImageView.setImageResource(R.drawable.ic_baseline_pause_48)
                    }
                    else {
                        it.playControlImageView.setImageResource(R.drawable.ic_baseline_play_arrow_48)
                    }
                }
            })
        }
    }

    private fun initRecyclerView(fragmentPlayerBinding: FragmentPlayerBinding) {
        playListAdapter = PlayListAdapter {

        }

        fragmentPlayerBinding.playListRecyclerView.apply {
            adapter = playListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initPlayListButton(fragmentPlayerBinding: FragmentPlayerBinding) {
        fragmentPlayerBinding.playlistImageView.setOnClickListener {
            if(model.currentPosition == -1) {
                return@setOnClickListener
            }

            fragmentPlayerBinding.playerViewGroup.isVisible = model.isWatchingPlayListView
            fragmentPlayerBinding.playListViewGroup.isVisible = model.isWatchingPlayListView.not()

            model.isWatchingPlayListView = !model.isWatchingPlayListView
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
                            response.body()?.let { musicDto->

                                model = musicDto.mapper()
                                setMusicList(model.getAdapterModels())
                                playListAdapter.submitList(model.getAdapterModels())

                            }
                        }

                        override fun onFailure(call: Call<MusicDto>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })
            }
    }

    private fun setMusicList(modelList: List<MusicModel>) {
        context?.let {
            player?.addMediaItems(modelList.map {musicModel ->
                MediaItem.Builder()
                    .setMediaId(musicModel.id.toString())
                    .setUri(musicModel.streamUrl)
                    .build()
            })

            player?.prepare()
            player?.play()
        }
    }

    companion object {
        // newInstance로 만드는 이유는 나중에 값을 넘겨줄 때 argument에 bundle을 넣어 넘겨주기 위해.
        fun newInstance(): PlayerFragment {
            return PlayerFragment()
        }
    }
}