package cl.talentodigital.probando_retoolapi.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    companion object{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://retoolapi.dev/cluuwe/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}