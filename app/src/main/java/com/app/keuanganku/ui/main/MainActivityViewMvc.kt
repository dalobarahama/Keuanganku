package com.app.keuanganku.ui.main

import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.ui.common.viewmvc.ObservableViewMvc

interface MainActivityViewMvc : ObservableViewMvc<MainActivityViewMvc.Listener> {

    interface Listener {
        fun inputSalary()
        fun onSalaryAllocationClicked()
    }

    fun setSalary(salaryEntity: SalaryEntity)
}