package com.example.meloncloneapp

import com.example.meloncloneapp.service.MusicDto
import com.example.meloncloneapp.service.MusicEntity

fun MusicEntity.mapper(id: Long): MusicModel =
    MusicModel(
        id = id,
        streamUrl = streamUrl,
        coverUrl = coverUrl,
        track = track,
        artist = artist
    )

// playerModel에 필요한 것은 playerMusicList<MusicModel>
// 서버로부터 받아오는 것은 MusicDto
// MusicDto(musics)에 매핑시켜 musicEntity를 받아오고
// musicEntity를 musicModel로 매핑시키는 과정
fun MusicDto.mapper(): PlayerModel =
    PlayerModel(
        playerMusicList = musics.mapIndexed { index, musicEntity ->
             musicEntity.mapper(index.toLong())
        }
    )
