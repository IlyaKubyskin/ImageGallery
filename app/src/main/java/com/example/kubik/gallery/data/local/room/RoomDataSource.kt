package com.example.kubik.gallery.data.local.room

import android.content.Context
import com.example.kubik.gallery.data.model.PixabayPojo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class RoomDataSource private constructor(context: Context) {

    var picturesDao = PicturesDatabase.getInstance(context.applicationContext)?.picturesData()

    suspend fun loadAllFromDb() =
        GlobalScope.async {
            picturesDao?.getAll()
        }.await()

    suspend fun insertAll(result: PixabayPojo) =
        GlobalScope.async {
            result.hits.forEach {
                picturesDao?.insertOneHit(SavedHit(it.previewURL, it.largeImageURL))
            }
        }.await()

    suspend fun deleteAllFromDb() =
        GlobalScope.async {
            picturesDao?.deleteAll()
        }.await()

    companion object {

        @JvmStatic
        private var instance: RoomDataSource? = null

        @JvmStatic
        @Synchronized
        fun get(context: Context): RoomDataSource {
            if (instance == null) {
                instance = RoomDataSource(context)
            }
            return instance as RoomDataSource
        }
    }
}