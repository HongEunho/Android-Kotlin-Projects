package com.example.meloncloneapp

data class PlayerModel(
    // private로 선언 이유는 getAdapterModels로만 접근 가능하게 하기 위
    private val playerMusicList: List<MusicModel> = emptyList(),
    val currentPosition: Int = -1,
    var isWatchingPlayListView: Boolean = true
) {
    fun getAdapterModels(): List<MusicModel> {
        return playerMusicList.mapIndexed { index, musicModel ->
            val newItem = musicModel.copy(
                isPlaying = index == currentPosition
            )
            newItem
        }
    }
}