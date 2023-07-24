package com.app.keuanganku.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.keuanganku.ui.common.dialog.addsalary.DialogAddSalaryViewMvc
import com.app.keuanganku.ui.common.dialog.addsalary.DialogAddSalaryViewMvcImpl
import com.app.keuanganku.ui.common.dialog.addsalaryallocation.DialogAddAllocationViewMvc
import com.app.keuanganku.ui.common.dialog.addsalaryallocation.DialogAddAllocationViewMvcImpl
import com.app.keuanganku.ui.main.MainActivityViewMvc
import com.app.keuanganku.ui.main.MainActivityViewMvcImpl

class ViewMvcFactory(private val layoutInflater: LayoutInflater) {

    fun getMainActivityViewMvc(parent: ViewGroup?): MainActivityViewMvc {
        return MainActivityViewMvcImpl(layoutInflater, parent)
    }

    fun getDialogAddSalaryViewMvc(parent: ViewGroup?): DialogAddSalaryViewMvc {
        return DialogAddSalaryViewMvcImpl(layoutInflater, parent)
    }

    fun getDialogAddAllocation(parent: ViewGroup?): DialogAddAllocationViewMvc {
        return DialogAddAllocationViewMvcImpl(layoutInflater, parent)
    }

}