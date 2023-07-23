package com.app.keuanganku.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.app.keuanganku.common.di.presentation.PresentationModule
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.ui.common.BaseActivity
import com.app.keuanganku.usecase.InsertSalaryUseCase
import com.app.keuanganku.usecase.UpdateSalaryUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class CustomDialog(private val title: String, private val salary: String) : DialogFragment(),
    CustomDialogViewMvcImpl.Listener {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(
            PresentationModule()
        )
    }

    private val injector get() = presentationComponent

    @Inject
    lateinit var insertSalaryUseCase: InsertSalaryUseCase

    @Inject
    lateinit var updateSalaryUseCase: UpdateSalaryUseCase

    private lateinit var viewMvc: CustomDialogViewMvcImpl

    private val coroutineScope get() = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewMvc = CustomDialogViewMvcImpl(layoutInflater, null)

        viewMvc.setTitle(title)
        viewMvc.setEditTextSalary(salary)

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

    override fun onClickPositiveButton(salaryEntity: SalaryEntity, salaryIsNull: Boolean) {
        coroutineScope.launch {
            if (salaryIsNull) {
                insertSalaryUseCase.insertSalary(salaryEntity)
            } else {
                updateSalaryUseCase.updateSalary(salaryEntity)
            }
        }

        dismiss()
    }

    override fun onClickNegativeButton() {
        dismiss()
    }

}