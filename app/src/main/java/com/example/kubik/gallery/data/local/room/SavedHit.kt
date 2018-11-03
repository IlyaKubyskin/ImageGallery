package com.example.kubik.gallery.data.local.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.kubik.gallery.ui.pictureslist.adapter.PicturesListAdapter

@Entity
data class SavedHit(
    var previewURL: String,
    var largeImageURL: String
) : PicturesListAdapter.PictureItem {
    override fun getPreviewUrl() = previewURL

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
