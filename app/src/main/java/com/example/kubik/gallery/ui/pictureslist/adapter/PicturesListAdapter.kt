package com.example.kubik.gallery.ui.pictureslist.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kubik.gallery.R
import com.example.kubik.gallery.ui.pictureslist.adapter.holder.PictureHolder

class PicturesListAdapter(private val callback: OnItemClickCallback) : RecyclerView.Adapter<PictureHolder>() {

    private val picturesList = mutableListOf<PictureItem>()

    fun setData(hits: List<PictureItem>, clear: Boolean) {
        if (clear) {
            picturesList.clear()
        }
        picturesList.addAll(hits)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.li_picture_holder, parent, false)
        return PictureHolder(view, callback)
    }

    override fun getItemCount(): Int = picturesList.size

    override fun onBindViewHolder(holder: PictureHolder, position: Int) = holder.bind(picturesList[position])

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface OnItemClickCallback {
        fun onImageClick(itemView: View, adapterPosition: Int)
    }

    interface PictureItem {
        fun getPreviewUrl(): String
    }
}