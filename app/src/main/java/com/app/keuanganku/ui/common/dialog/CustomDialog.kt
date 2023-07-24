package com.app.keuanganku.ui.common.dialog

import android.app.Dialog
import android.os.Bundle
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.ui.common.ViewMvcFactory
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialog
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialogViewMvc
import javax.inject.Inject

class CustomDialog(private val title: String, private val salaryEntity: SalaryEntity) :
    BaseCustomDialog(),
    BaseCustomDialogViewMvc.Listener {

    @Inject
    lateinit var dialogEventBus: DialogEventBus

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    private lateinit var viewMvc: CustomDialogAddSalaryViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewMvc = viewMvcFactory.getCustomDialogAddSalaryViewMvc(null)
        viewMvc.setTitle(title)
        viewMvc.setEditTextSalary(salaryEntity.salary.toString())
        viewMvc.setSalaryEntity(salaryEntity)

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

    override fun onClickPositiveButton(salaryEntity: SalaryEntity) {
        dialogEventBus.postEvent(CustomDialogEvent(CustomDialogEvent.Button.POSITIVE, salaryEntity))
        dismiss()
    }

    override fun onClickNegativeButton() {
        dialogEventBus.postEvent(CustomDialogEvent(CustomDialogEvent.Button.NEGATIVE))
        dismiss()
    }

}