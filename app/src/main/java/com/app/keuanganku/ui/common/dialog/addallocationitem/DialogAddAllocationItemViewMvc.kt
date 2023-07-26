package com.app.keuanganku.ui.common.dialog.addallocationitem

import com.app.keuanganku.data.entity.AllocationItem
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialogViewMvc

interface DialogAddAllocationItemViewMvc : BaseCustomDialogViewMvc {

    fun setSalaryAllocationItem(allocationItem: AllocationItem?)

}