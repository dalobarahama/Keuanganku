package com.app.keuanganku.ui.common.dialog.addsalary

import android.app.Dialog
import android.os.Bundle
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.ui.common.ViewMvcFactory
import com.app.keuanganku.ui.common.dialog.CustomDialogEvent
import com.app.keuanganku.ui.common.dialog.DialogEventBus
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialog
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialogViewMvc
import com.app.keuanganku.usecase.UpdateSalaryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DialogAddSalary(private val title: String, private val salaryEntity: SalaryEntity) :
    BaseCustomDialog(),
    BaseCustomDialogViewMvc.Listener {

    @Inject
    lateinit var dialogEventBus: DialogEventBus

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var updateSalaryUseCase: UpdateSalaryUseCase

    private lateinit var viewMvc: DialogAddSalaryViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewMvc = viewMvcFactory.getDialogAddSalaryViewMvc(null)
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

    override fun onClickPositiveButton(objects: Any) {
        coroutineScope.launch {
            updateSalaryUseCase.updateSalary(objects as SalaryEntity)
        }

        dialogEventBus.postEvent(
            CustomDialogEvent(
                CustomDialogEvent.Button.POSITIVE
            )
        )
        dismiss()
    }

    override fun onClickNegativeButton() {
        dialogEventBus.postEvent(CustomDialogEvent(CustomDialogEvent.Button.NEGATIVE))
        dismiss()
    }

}