package com.app.keuanganku.ui

import android.os.Bundle
import android.widget.Toast
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.ui.common.BaseActivity
import com.app.keuanganku.ui.common.dialog.CustomDialogEvent
import com.app.keuanganku.ui.common.dialog.DialogEventBus
import com.app.keuanganku.usecase.GetSalaryUseCase
import com.app.keuanganku.usecase.UpdateSalaryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityViewMvc.Listener, DialogEventBus.Listener {

    private lateinit var viewMvc: MainActivityViewMvcImpl

    @Inject
    lateinit var updateSalaryUseCase: UpdateSalaryUseCase

    @Inject
    lateinit var getSalaryUseCase: GetSalaryUseCase

    @Inject
    lateinit var dialogEventBus: DialogEventBus

    private lateinit var salaryEntity: SalaryEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)

        getSalaryFromUseCase()

        viewMvc = MainActivityViewMvcImpl(layoutInflater, null)
        setContentView(viewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        getSalaryFromUseCase()
        viewMvc.registerListener(this)
        dialogEventBus.registerListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewMvc.unregisterListener(this)
        dialogEventBus.unregisterListener(this)
    }

    private fun getSalaryFromUseCase() {
        coroutineScope.launch {
            salaryEntity = getSalaryUseCase.getSalary()
            setSalary()
        }
    }

    private fun setSalary() {
        viewMvc.setSalary(salaryEntity)
    }

    override fun inputSalary() {
        val customDialog = CustomDialog("Input Salary", salaryEntity)

        customDialog.show(supportFragmentManager, "inputSalary")
    }

    override fun onDialogEvent(event: Any) {
        if (event is CustomDialogEvent) when (event.getClickedButton()) {
            CustomDialogEvent.Button.POSITIVE -> {
                coroutineScope.launch {
                    event.getSalaryEntity()?.let { updateSalaryUseCase.updateSalary(it) }
                }

                setSalary()
            }
            CustomDialogEvent.Button.NEGATIVE -> {
                Toast.makeText(this, "Negative button clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }
}