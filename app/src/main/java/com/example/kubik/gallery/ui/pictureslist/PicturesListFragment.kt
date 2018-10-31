package com.example.kubik.gallery.ui.pictureslist

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kubik.gallery.R
import com.example.kubik.gallery.data.Repository
import com.example.kubik.gallery.ui.MainActivity
import com.example.kubik.gallery.ui.base.BaseFragment
import com.example.kubik.gallery.ui.largeimage.LargeImagePager
import com.example.kubik.gallery.ui.pictureslist.adapter.PicturesListAdapter
import kotlinx.android.synthetic.main.fr_bot_toolbar.*
import kotlinx.android.synthetic.main.fr_recycler.*

class PicturesListFragment : BaseFragment(), PicturesListView, PicturesListAdapter.AdapterCallback {

    @InjectPresenter
    lateinit var presenter: PicturesListPresenter

    @ProvidePresenter
    fun providePresenter(): PicturesListPresenter {
        val repository = Repository.get(activity!!)
        return PicturesListPresenter(repository)
    }

    private val adapter = PicturesListAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.layoutManager = GridLayoutManager(context, 2)
        recycler.adapter = adapter

        botAppBar.replaceMenu(R.menu.menu_bot)

        val search = botAppBar.findViewById<SearchView>(R.id.appbarSearch)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.search(query)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                // nothing
                return false
            }
        })
    }

    @LayoutRes
    override fun getLayout(): Int = R.layout.fr_bot_toolbar

    override fun setPictures(hits: List<PicturesListAdapter.PictureItem>, clear: Boolean) = adapter.setData(hits, true)

    override fun onImageClick(itemView: View, adapterPosition: Int) {
        MainActivity.position = adapterPosition

        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, getNextFragment())
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun getNextFragment() = LargeImagePager.newInstance()

    companion object {

        @JvmStatic
        fun newInstance() = PicturesListFragment()
    }
}