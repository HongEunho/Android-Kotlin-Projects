package com.example.airbnb_practice

import retrofit2.Call
import retrofit2.http.GET

interface HouseService {
    @GET("/v3/e38300f9-48b6-4344-b2be-284cf2ca6659")
    fun getHouseList(): Call<HouseDto>
}