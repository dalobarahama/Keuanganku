package com.app.keuanganku.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.keuanganku.ui.common.dialog.CustomDialogAddSalaryViewMvc
import com.app.keuanganku.ui.common.dialog.CustomDialogAddSalaryViewViewMvcImpl
import com.app.keuanganku.ui.main.MainActivityViewMvc
import com.app.keuanganku.ui.main.MainActivityViewMvcImpl

class ViewMvcFactory(private val layoutInflater: LayoutInflater) {

    fun getMainActivityViewMvc(parent: ViewGroup?): MainActivityViewMvc {
        return MainActivityViewMvcImpl(layoutInflater, parent)
    }

    fun getCustomDialogAddSalaryViewMvc(parent: ViewGroup?): CustomDialogAddSalaryViewMvc {
        return CustomDialogAddSalaryViewViewMvcImpl(layoutInflater, parent)
    }

}