package com.example.kubik.gallery.data

import android.content.Context
import com.example.kubik.gallery.data.local.room.RoomDataSource
import com.example.kubik.gallery.data.model.PixabayPojo
import com.example.kubik.gallery.data.remote.PicturesDataSource
import com.example.kubik.gallery.data.remote.RetrofitServiceProvider

class Repository private constructor(context: Context) {

    private val picturesDataSource = RetrofitServiceProvider.getServiceInstance(PicturesDataSource::class.java)
    private val database = RoomDataSource.get(context.applicationContext)

    suspend fun getPictures(query: String)= picturesDataSource.getPicsInfo(query = query).await()

    suspend fun getAllDataFromDb() = database.loadAllFromDb()

    suspend fun deleteAllDataFromDb() = database.deleteAllFromDb()

    suspend fun insertHitsIntoDb(result: PixabayPojo) = database.insertAll(result)

    companion object {

        @JvmStatic
        private var instance: Repository? = null

        @JvmStatic
        @Synchronized
        fun get(context: Context): Repository {
            if (instance == null) {
                instance = Repository(context)
            }
            return instance as Repository
        }
    }
}