package com.example.meloncloneapp.service

import retrofit2.Call
import retrofit2.http.GET

interface MusicService {

    @GET("/v3/0ec57b5b-8b73-4768-9165-4fd98c8cb995")
    fun listMusics(): Call<MusicDto>
}