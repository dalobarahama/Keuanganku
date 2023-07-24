package com.app.keuanganku.ui.main

import android.os.Bundle
import android.widget.Toast
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.ui.common.BaseActivity
import com.app.keuanganku.ui.common.ViewMvcFactory
import com.app.keuanganku.ui.common.dialog.CustomDialogEvent
import com.app.keuanganku.ui.common.dialog.DialogEventBus
import com.app.keuanganku.ui.common.dialog.addsalary.DialogAddSalary
import com.app.keuanganku.ui.common.dialog.addsalaryallocation.DialogAddAllocation
import com.app.keuanganku.usecase.salary.GetSalaryUseCase
import com.app.keuanganku.usecase.salary.UpdateSalaryUseCase
import com.app.keuanganku.usecase.salaryallocation.GetSalaryAllocationUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityViewMvc.Listener, DialogEventBus.Listener {

    private lateinit var viewMvc: MainActivityViewMvc

    @Inject
    lateinit var updateSalaryUseCase: UpdateSalaryUseCase

    @Inject
    lateinit var getSalaryUseCase: GetSalaryUseCase

    @Inject
    lateinit var getSalaryAllocationUseCase: GetSalaryAllocationUseCase

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var dialogEventBus: DialogEventBus

    private lateinit var salaryEntity: SalaryEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)

        fetchSalary()

        viewMvc = viewMvcFactory.getMainActivityViewMvc(null)
        setContentView(viewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        fetchSalary()
        fetchSalaryAllocation()
        viewMvc.registerListener(this)
        dialogEventBus.registerListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewMvc.unregisterListener(this)
        dialogEventBus.unregisterListener(this)
    }

    private fun fetchSalary() {
        coroutineScope.launch {
            salaryEntity = getSalaryUseCase.getSalary()
            viewMvc.setSalary(salaryEntity)
        }
    }

    override fun inputSalary() {
        val dialogAddSalary = DialogAddSalary("Input Salary", salaryEntity)

        dialogAddSalary.show(supportFragmentManager, "inputSalary")
    }

    override fun addSalaryAllocation() {
        val dialogAddAllocation = DialogAddAllocation()

        dialogAddAllocation.show(supportFragmentManager, "addAllocation")
    }

    override fun onSalaryAllocationItemClicked(salaryAllocation: SalaryAllocation) {
        Toast.makeText(this, "${salaryAllocation.title} clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onDialogEvent(event: Any) {
        if (event is CustomDialogEvent) when (event.getClickedButton()) {
            CustomDialogEvent.Button.POSITIVE -> {
                fetchSalary()
                fetchSalaryAllocation()
            }
            CustomDialogEvent.Button.NEGATIVE -> {
                Toast.makeText(this, "Negative button clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchSalaryAllocation() {
        coroutineScope.launch {
            if (getSalaryAllocationUseCase.getSalaryAllocation().isNotEmpty()) {
                viewMvc.bindSalaryAllocation(getSalaryAllocationUseCase.getSalaryAllocation())
            }
        }
    }
}