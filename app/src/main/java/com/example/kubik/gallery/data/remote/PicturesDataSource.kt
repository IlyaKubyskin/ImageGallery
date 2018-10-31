package com.example.kubik.gallery.data.remote

import com.example.kubik.gallery.BuildConfig
import com.example.kubik.gallery.data.model.PixabayPojo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PicturesDataSource {

    @GET("api/")
    fun getPicsInfo(@Query("key") apiKey: String = BuildConfig.API_KEY,
                    @Query("q") query: String): Deferred<PixabayPojo>

}