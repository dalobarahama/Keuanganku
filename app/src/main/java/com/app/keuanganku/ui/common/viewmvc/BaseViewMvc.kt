package com.app.keuanganku.ui.common.viewmvc

import android.content.Context
import android.view.View

abstract class BaseViewMvc : ViewMvc {

    private lateinit var rootView: View

    override fun getRootView(): View = rootView

    protected fun setRootView(rootView: View) {
        this.rootView = rootView
    }

    protected fun <T : View?> findViewById(id: Int): T {
        return getRootView().findViewById<T>(id)
    }

    protected fun getContext(): Context {
        return getRootView().context
    }
}