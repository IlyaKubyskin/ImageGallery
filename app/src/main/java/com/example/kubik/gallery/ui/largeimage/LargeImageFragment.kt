package com.example.kubik.gallery.ui.largeimage

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.kubik.gallery.R
import com.example.kubik.gallery.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fr_large_image.*

class LargeImageFragment : BaseFragment() {

    private var imgUrl = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgUrl = arguments?.getString(IMG_URL) ?: ""

        Glide.with(this).load(imgUrl).addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progress.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progress.visibility = View.GONE
                return false
            }
        }).into(largeImage)
    }

    @LayoutRes
    override fun getLayout(): Int = R.layout.fr_large_image

    companion object {

        private const val IMG_URL = "imgUrl"

        @JvmStatic
        fun newInstance(imgUrl: String): LargeImageFragment {
            val fragment = LargeImageFragment()
            fragment.arguments = Bundle().apply {
                putString(IMG_URL, imgUrl)
            }
            return fragment
        }
    }
}