package com.app.keuanganku.ui.main

import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.ui.common.ObservableViewMvc

interface MainActivityViewMvc : ObservableViewMvc<MainActivityViewMvc.Listener> {

    interface Listener {
        fun inputSalary(salaryEntity: SalaryEntity, salaryIsNull: Boolean)
        fun addSalary(salary: Int)
    }

}