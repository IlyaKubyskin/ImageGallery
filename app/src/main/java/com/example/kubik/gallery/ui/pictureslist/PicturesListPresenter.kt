package com.example.kubik.gallery.ui.pictureslist

import com.arellomobile.mvp.InjectViewState
import com.example.kubik.gallery.data.Repository
import com.example.kubik.gallery.ui.base.BasePresenter

@InjectViewState
class PicturesListPresenter(private val repository: Repository) : BasePresenter<PicturesListView>() {

    var query = "yellow+flowers"

    override fun onFirstViewAttach() = getPictures()

    fun search(query: String?) {
        if (query != null) {
            this.query = query
            getPictures()
        }
    }

    private fun getPictures() = startLoadingJob {
        val cache = repository.getAllDataFromDb()
        if (cache != null && cache.isNotEmpty()) {
            val lastQuery = cache[0].query
            if (query == lastQuery) {
                viewState.setPictures(cache, true)
            } else {
                loadRemote(query)
            }
        } else {
            loadRemote(query)
        }
    }

    private suspend fun loadRemote(query: String) {
        val result = repository.getPictures(query)
        viewState.setPictures(result.hits, true)
        repository.deleteAllDataFromDb()
        repository.insertIntoDb(result, query)
    }
}