package com.example.kubik.gallery.ui.largeimage

import com.arellomobile.mvp.InjectViewState
import com.example.kubik.gallery.data.Repository
import com.example.kubik.gallery.ui.base.BasePresenter

@InjectViewState
class LargeImagePresenter(private val repository: Repository) : BasePresenter<LargeImageView>() {

    fun getPictures() = startLoadingJob {
        val pics = repository.getAllDataFromDb()
        if (pics != null) {
            viewState.setPictures(pics)
        }
    }
}