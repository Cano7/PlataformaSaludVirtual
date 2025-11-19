package com.example.plataformasaludvirtual.tablero.Consultas

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiCliente {
    private const val BASE_URL_CUBO = "https://2apicubossvirtualdw20251118180019-bvakezh0angud0ef.canadacentral-01.azurewebsites.net/api/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_CUBO)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val cubeApiService: CubeApiService = retrofit.create(CubeApiService::class.java)
}