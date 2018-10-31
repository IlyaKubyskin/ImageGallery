package com.example.kubik.gallery.data.local.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.kubik.gallery.ui.pictureslist.adapter.PicturesListAdapter

@Entity
class SavedHit : PicturesListAdapter.PictureItem {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
    var previewURL: String = ""
    var largeImageURL: String = ""
    var query = ""
}