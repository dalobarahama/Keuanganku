package com.app.keuanganku.ui.common.dialog.basedialog

import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.ui.common.viewmvc.ObservableViewMvc

interface BaseCustomDialogViewMvc : ObservableViewMvc<BaseCustomDialogViewMvc.Listener> {

    interface Listener {
        fun onClickPositiveButton(salaryEntity: SalaryEntity)
        fun onClickNegativeButton()
    }

    fun setTitle(title: String)

}