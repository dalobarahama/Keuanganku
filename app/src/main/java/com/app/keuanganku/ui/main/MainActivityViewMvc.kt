package com.app.keuanganku.ui.main

import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.ui.common.viewmvc.ObservableViewMvc

interface MainActivityViewMvc : ObservableViewMvc<MainActivityViewMvc.Listener> {

    interface Listener {
        fun inputSalary()
        fun addSalaryAllocation()
        fun addSalaryAllocationItem(salaryAllocation: SalaryAllocation)
        fun onSalaryAllocationLongClicked(salaryAllocation: SalaryAllocation)
    }

    fun setSalary(salaryEntity: SalaryEntity)
    fun bindSalaryAllocation(salaryAllocations: List<SalaryAllocation>)
}