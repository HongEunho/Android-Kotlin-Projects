package com.example.airbnb.api

import com.example.airbnb.model.HouseDto
import retrofit2.Call
import retrofit2.http.GET

interface HouseService {
    @GET("/v3/b1d5563e-960e-42a9-9933-a5f87698c69c")
    fun getHouseList(): Call<HouseDto>
}