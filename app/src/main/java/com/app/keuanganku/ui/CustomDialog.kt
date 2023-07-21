package com.app.keuanganku.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.app.keuanganku.common.di.presentation.PresentationModule
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.ui.common.BaseActivity
import com.app.keuanganku.ui.common.dialog.CustomDialogEvent
import com.app.keuanganku.ui.common.dialog.DialogEventBus
import javax.inject.Inject

class CustomDialog(private val title: String, private val salaryEntity: SalaryEntity) :
    DialogFragment(),
    CustomDialogViewMvcImpl.Listener {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(
            PresentationModule()
        )
    }

    private val injector get() = presentationComponent

    @Inject
    lateinit var dialogEventBus: DialogEventBus

    private lateinit var viewMvc: CustomDialogViewMvcImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewMvc = CustomDialogViewMvcImpl(layoutInflater, null)

        viewMvc.setTitle(title)
        viewMvc.setEditTextSalary(salaryEntity.salary.toString())
        viewMvc.setSalaryEntity(salaryEntity)

        val dialog = Dialog(requireContext())
        dialog.setContentView(viewMvc.rootView)

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