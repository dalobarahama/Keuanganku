package com.app.keuanganku.ui.common.dialog.addsalaryallocation

import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialogViewMvc

interface DialogAddAllocationViewMvc : BaseCustomDialogViewMvc {

    fun setAllocationTitle(title: String)
    fun setAllocationAmount(amount: Int)

}