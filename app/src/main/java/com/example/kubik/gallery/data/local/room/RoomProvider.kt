package com.example.kubik.gallery.data.local.room

import android.content.Context
import com.example.kubik.gallery.data.model.PixabayPojo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class RoomProvider private constructor(context: Context) {

    var database = PicturesDatabase.getInstance(context.applicationContext)?.picturesData()

    suspend fun loadAllFromDb() =
        GlobalScope.async {
            database?.getAll()
        }.await()

    suspend fun insertAll(result: PixabayPojo, query: String) =
        GlobalScope.async {
            result.hits.forEach {
                val objectForSave = SavedHit()
                objectForSave.largeImageURL = it.largeImageURL
                objectForSave.previewURL = it.previewURL
                objectForSave.query = query
                database?.insertOne(objectForSave)
            }
        }.await()

    suspend fun deleteAllFromDb() =
        GlobalScope.async {
            database?.deleteAll()
        }.await()

    companion object {

        @JvmStatic
        private var instance: RoomProvider? = null

        @JvmStatic
        @Synchronized
        fun get(context: Context): RoomProvider {
            if (instance == null) {
                instance = RoomProvider(context)
            }
            return instance as RoomProvider
        }
    }
}