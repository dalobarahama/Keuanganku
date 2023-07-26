package com.app.keuanganku.ui.common.dialog.addallocationitem

import android.app.Dialog
import android.os.Bundle
import com.app.keuanganku.data.entity.AllocationItem
import com.app.keuanganku.ui.common.ViewMvcFactory
import com.app.keuanganku.ui.common.dialog.CustomDialogEvent
import com.app.keuanganku.ui.common.dialog.DialogEventBus
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialog
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialogViewMvc
import com.app.keuanganku.usecase.allocationitem.InsertAllocationItemUseCase
import com.app.keuanganku.usecase.allocationitem.UpdateAllocationItemUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DialogAddAllocationItem(val allocationItem: AllocationItem? = null) : BaseCustomDialog(),
    BaseCustomDialogViewMvc.Listener {

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var dialogEventBus: DialogEventBus

    @Inject
    lateinit var insertAllocationItemUseCase: InsertAllocationItemUseCase

    @Inject
    lateinit var updateAllocationItemUseCase: UpdateAllocationItemUseCase

    lateinit var viewMvc: DialogAddAllocationItemViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewMvc = viewMvcFactory.getDialogAddAllocationItemViewMvc(null)
        viewMvc.setTitle("Add Allocation Item")
        viewMvc.setSalaryAllocationItem(allocationItem)

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
            val item = objects as AllocationItem

            if (allocationItem?.id != null && allocationItem.id == item.id) {
                updateAllocationItemUseCase.updateSalaryAllocation(item)
            } else {
                insertAllocationItemUseCase.insertAllocation(item)
            }
        }
        dialogEventBus.postEvent(CustomDialogEvent(CustomDialogEvent.Button.POSITIVE))
        dismiss()
    }

    override fun onClickNegativeButton() {
        dialogEventBus.postEvent(CustomDialogEvent(CustomDialogEvent.Button.NEGATIVE))
        dismiss()
    }

}