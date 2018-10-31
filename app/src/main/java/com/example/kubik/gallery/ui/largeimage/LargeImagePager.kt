package com.example.kubik.gallery.ui.largeimage

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kubik.gallery.R
import com.example.kubik.gallery.data.Repository
import com.example.kubik.gallery.data.local.room.SavedHit
import com.example.kubik.gallery.ui.MainActivity
import com.example.kubik.gallery.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fr_pager_large_image.*

class LargeImagePager : BaseFragment(), LargeImageView{

    private var picturesList = mutableListOf<SavedHit>()

    @InjectPresenter
    lateinit var presenter: LargeImagePresenter

    @ProvidePresenter
    fun providePresenter(): LargeImagePresenter {
        val repository = Repository.get(activity!!)
        return LargeImagePresenter(repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getPictures()

        viewPager.adapter = object : FragmentStatePagerAdapter(fragmentManager) {

            override fun getItem(p0: Int): Fragment {
                return LargeImageFragment.newInstance(picturesList[p0].largeImageURL)
            }

            override fun getCount(): Int = picturesList.size
        }

        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                MainActivity.position = position
            }
        })
    }

    override fun setPictures(hits: List<SavedHit>) {
        picturesList.addAll(hits)
        viewPager.adapter?.notifyDataSetChanged()
        viewPager.setCurrentItem(MainActivity.position, false)
    }

    @LayoutRes
    override fun getLayout(): Int = R.layout.fr_pager_large_image

    companion object {

        @JvmStatic
        fun newInstance() = LargeImagePager()
    }
}