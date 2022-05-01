package com.example.youtubecloneapp.service

import com.example.youtubecloneapp.dto.VideoDto
import retrofit2.Call
import retrofit2.http.GET

interface VideoService {

    @GET("/v3/85a0f1a9-cdfa-46aa-b8a7-b6a579de6de8")
    fun listVideos(): Call<VideoDto>
}