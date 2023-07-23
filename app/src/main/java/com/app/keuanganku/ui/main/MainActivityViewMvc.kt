package com.app.keuanganku.ui.main

import com.app.keuanganku.ui.common.ObservableViewMvc

interface MainActivityViewMvc : ObservableViewMvc<MainActivityViewMvc.Listener> {

    interface Listener {
        fun inputSalary()
    }

}