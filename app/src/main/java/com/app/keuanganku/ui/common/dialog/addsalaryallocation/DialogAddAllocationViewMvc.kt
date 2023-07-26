package com.app.keuanganku.ui.common.dialog.addsalaryallocation

import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialogViewMvc

interface DialogAddAllocationViewMvc : BaseCustomDialogViewMvc {

    fun setSalaryAllocation(salaryAllocation: SalaryAllocation?)

}