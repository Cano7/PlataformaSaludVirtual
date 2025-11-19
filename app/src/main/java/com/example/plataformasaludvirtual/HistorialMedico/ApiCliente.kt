
package com.example.plataformasaludvirtual.HistorialMedico

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object ApiCliente {
    private const val BASE_URL = "https://saludvirtualapi20251107142651-eqezcwgwd7bxf4fm.mexicocentral-01.azurewebsites.net/api/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val historialApiService: HistorialApiService = retrofit.create(HistorialApiService::class.java)
}
