package com.app.keuanganku.ui

import com.app.keuanganku.ui.common.ObservableViewMvc

interface MainActivityViewMvc : ObservableViewMvc<MainActivityViewMvc.Listener> {

    interface Listener {
        fun inputSalary()
    }

}