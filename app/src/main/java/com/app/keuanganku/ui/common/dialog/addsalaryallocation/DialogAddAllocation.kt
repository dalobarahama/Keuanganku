package com.app.keuanganku.ui.common.dialog.addsalaryallocation

import android.app.Dialog
import android.os.Bundle
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.ui.common.ViewMvcFactory
import com.app.keuanganku.ui.common.dialog.CustomDialogEvent
import com.app.keuanganku.ui.common.dialog.DialogEventBus
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialog
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialogViewMvc
import com.app.keuanganku.usecase.salaryallocation.InsertSalaryAllocationUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DialogAddAllocation : BaseCustomDialog(), BaseCustomDialogViewMvc.Listener {

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var dialogEventBus: DialogEventBus

    @Inject
    lateinit var insertSalaryAllocationUseCase: InsertSalaryAllocationUseCase

    lateinit var viewMvc: DialogAddAllocationViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewMvc = viewMvcFactory.getDialogAddAllocationViewMvc(null)
        viewMvc.setTitle("Add Allocation")

        val dialog = Dialog(requireContext())
        dialog.setContentView(viewMvc.getRootView())

        return dialog
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
    }

    override fun onClickPositiveButton(objects: Any) {
        coroutineScope.launch {
            insertSalaryAllocationUseCase.insertSalary(objects as SalaryAllocation)
        }
        dialogEventBus.postEvent(CustomDialogEvent(CustomDialogEvent.Button.POSITIVE))
        dismiss()
    }

    override fun onClickNegativeButton() {
        dialogEventBus.postEvent(CustomDialogEvent(CustomDialogEvent.Button.NEGATIVE))
        dismiss()
    }

}