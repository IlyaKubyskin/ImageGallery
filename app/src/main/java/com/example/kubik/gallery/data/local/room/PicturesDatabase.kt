package com.example.kubik.gallery.data.local.room

import android.arch.persistence.room.*
import android.content.Context

@Database(entities = [SavedHit::class], version = 1)
abstract class PicturesDatabase : RoomDatabase() {

    abstract fun picturesData(): PicturesDao

    companion object {
        private var instance: PicturesDatabase? = null

        fun getInstance(context: Context): PicturesDatabase? {
            if (instance == null) {
                synchronized(PicturesDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        PicturesDatabase::class.java, "savedhit")
                        .build()
                }
            }
            return instance
        }
    }
}