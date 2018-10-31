package com.example.kubik.gallery.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.kubik.gallery.R

abstract class SingleFragmentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, createFragment())
                .commit()
        }
    }

    override fun getLayout(): Int = R.layout.ac_single_frame

    abstract fun createFragment(): Fragment
}