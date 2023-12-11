package com.app.keuanganku.ui.main.salaryallocationitem

import com.app.keuanganku.data.entity.DeductionItem
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.ui.common.viewmvc.ObservableViewMvc

interface SalaryAllocationItemViewMvc : ObservableViewMvc<SalaryAllocationItemViewMvc.Listener> {

    interface Listener {
        fun onItemClicked(salaryAllocation: SalaryAllocation)
        fun onItemLongClick(salaryAllocation: SalaryAllocation)
    }

    fun bindSalaryAllocation(salaryAllocation: SalaryAllocation)
    fun bindDeductionItem(deductionItems: List<DeductionItem>)

}