package com.example.kubik.gallery.ui.pictureslist

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.kubik.gallery.ui.pictureslist.adapter.PicturesListAdapter

interface PicturesListView : MvpView {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun setPictures(hits: List<PicturesListAdapter.PictureItem>, clear: Boolean)
}