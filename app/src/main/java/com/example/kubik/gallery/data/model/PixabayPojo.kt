package com.example.kubik.gallery.data.model

import com.example.kubik.gallery.ui.pictureslist.adapter.PicturesListAdapter

data class PixabayPojo(
    var hits: List<Hit>
)

data class Hit(
    var id: String,
    var previewURL: String,
    var imageURL: String,
    var largeImageURL: String,
    var fullHDImageURL: String
) : PicturesListAdapter.PictureItem {
    override fun getPreviewUrl() = previewURL
}