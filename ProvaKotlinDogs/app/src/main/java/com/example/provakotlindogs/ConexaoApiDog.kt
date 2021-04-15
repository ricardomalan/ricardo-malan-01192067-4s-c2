package com.example.provakotlindogs

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConexaoApiDog {

    fun criar(): ApiDog {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://5f861cfdc8a16a0016e6aacd.mockapi.io/bandtec-api/ ")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiDog::class.java)

        return api
    }

}