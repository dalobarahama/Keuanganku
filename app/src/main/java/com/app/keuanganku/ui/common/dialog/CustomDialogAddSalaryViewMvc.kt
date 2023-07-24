package com.app.keuanganku.ui.common.dialog

import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialogViewMvc

interface CustomDialogAddSalaryViewMvc : BaseCustomDialogViewMvc {

    fun setEditTextSalary(salary: String)

    fun setSalaryEntity(salaryEntity: SalaryEntity)

}