package com.app.keuanganku.ui.common.dialog.addallocationitem

import com.app.keuanganku.data.entity.DeductionItem
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialogViewMvc

interface DialogAddDeductionItemViewMvc : BaseCustomDialogViewMvc {

    fun setSalaryAllocationItem(deductionItem: DeductionItem?)

}