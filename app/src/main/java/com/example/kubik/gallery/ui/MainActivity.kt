package com.example.kubik.gallery.ui

import android.support.v4.app.Fragment
import com.example.kubik.gallery.ui.base.SingleFragmentActivity
import com.example.kubik.gallery.ui.pictureslist.PicturesListFragment

class MainActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment = PicturesListFragment.newInstance()
}