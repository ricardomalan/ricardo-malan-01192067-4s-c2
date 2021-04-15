package com.example.provakotlindogs

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiDog {

    @GET("cachorros")
    fun get(): Call<List<Dogs>>

    @GET("cachorros/{id}")
    fun get(@Path("id") id:Int): Call<Dogs>

}