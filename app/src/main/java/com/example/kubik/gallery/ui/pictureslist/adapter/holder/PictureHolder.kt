package com.example.kubik.gallery.ui.pictureslist.adapter.holder

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.kubik.gallery.ui.pictureslist.adapter.PicturesListAdapter
import kotlinx.android.synthetic.main.li_picture_holder.view.*

class PictureHolder(
    itemView: View,
    private val callback: PicturesListAdapter.OnItemClickCallback
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.holderImageView.setOnClickListener { callback.onImageClick(itemView, adapterPosition) }
    }

    fun bind(hit: PicturesListAdapter.PictureItem) {
        Glide.with(itemView.context).load(hit.getPreviewUrl()).addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                itemView.progressHolder.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                itemView.progressHolder.visibility = View.GONE
                return false
            }
        }).into(itemView.holderImageView)
    }
}