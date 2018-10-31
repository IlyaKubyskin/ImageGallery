package com.example.kubik.gallery.ui.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

open class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    private var job: Job? = null

    fun startLoadingJob(block: suspend () -> Unit) {
        if (job != null) {
            job?.cancel()
            job = null
        }
        job = GlobalScope.launch(Dispatchers.Main) {
            try {
                block.invoke()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}