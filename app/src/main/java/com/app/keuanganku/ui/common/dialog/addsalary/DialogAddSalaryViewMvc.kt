package com.app.keuanganku.ui.common.dialog.addsalary

import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialogViewMvc

interface DialogAddSalaryViewMvc : BaseCustomDialogViewMvc {

    interface Listener {

    }

    fun setEditTextSalary(salary: String)
    fun setSalaryEntity(salaryEntity: SalaryEntity)

}