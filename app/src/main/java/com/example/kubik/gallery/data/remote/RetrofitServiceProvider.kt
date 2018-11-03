package com.example.kubik.gallery.data.remote

import com.example.kubik.gallery.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceProvider {
    @JvmStatic
    private val retrofit: Retrofit by lazy {
        getRetrofitInstance()
    }

    @JvmStatic
    fun <T> getServiceInstance(clazz: Class<T>): T = retrofit.create(clazz)

    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}