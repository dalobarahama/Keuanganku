package com.app.keuanganku.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.keuanganku.ui.common.dialog.addsalary.DialogAddSalaryViewMvc
import com.app.keuanganku.ui.common.dialog.addsalary.DialogAddSalaryViewMvcImpl
import com.app.keuanganku.ui.common.dialog.addsalaryallocation.DialogAddAllocationViewMvc
import com.app.keuanganku.ui.common.dialog.addsalaryallocation.DialogAddAllocationViewMvcImpl
import com.app.keuanganku.ui.main.MainActivityViewMvc
import com.app.keuanganku.ui.main.MainActivityViewMvcImpl
import com.app.keuanganku.ui.main.allocationitem.AllocationItemViewMvc
import com.app.keuanganku.ui.main.allocationitem.AllocationItemViewMvcImpl
import com.app.keuanganku.ui.main.salaryallocationitem.SalaryAllocationItemViewMvc
import com.app.keuanganku.ui.main.salaryallocationitem.SalaryAllocationItemViewMvcImpl

class ViewMvcFactory(private val layoutInflater: LayoutInflater) {

    fun getMainActivityViewMvc(parent: ViewGroup?): MainActivityViewMvc {
        return MainActivityViewMvcImpl(layoutInflater, parent, this)
    }

    fun getDialogAddSalaryViewMvc(parent: ViewGroup?): DialogAddSalaryViewMvc {
        return DialogAddSalaryViewMvcImpl(layoutInflater, parent)
    }

    fun getDialogAddAllocationViewMvc(parent: ViewGroup?): DialogAddAllocationViewMvc {
        return DialogAddAllocationViewMvcImpl(layoutInflater, parent)
    }

    fun getSalaryAllocationItemViewMvc(parent: ViewGroup?): SalaryAllocationItemViewMvc {
        return SalaryAllocationItemViewMvcImpl(layoutInflater, parent)
    }

    fun getAllocationItemViewMvc(parent: ViewGroup?): AllocationItemViewMvc {
        return AllocationItemViewMvcImpl(layoutInflater, parent)
    }

}