package com.example.kubik.gallery.ui.largeimage

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.kubik.gallery.data.local.room.SavedHit

interface LargeImageView : MvpView {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun setPictures(hits: List<SavedHit>)
}