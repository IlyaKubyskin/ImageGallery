package com.example.kubik.gallery.data.local.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PicturesDao {

    @Query("SELECT * FROM savedhit")
    fun getAll(): List<SavedHit>

    @Insert
    fun insertOneHit(hit: SavedHit)

    @Query("DELETE FROM savedhit")
    fun deleteAll()
}