package com.app.keuanganku.ui.main

import android.os.Bundle
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.ui.CustomDialog
import com.app.keuanganku.ui.common.BaseActivity
import com.app.keuanganku.usecase.GetSalaryUseCase
import com.app.keuanganku.usecase.InsertSalaryUseCase
import com.app.keuanganku.usecase.UpdateSalaryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityViewMvc.Listener {

    private lateinit var viewMvc: MainActivityViewMvcImpl

    @Inject
    lateinit var insertSalaryUseCase: InsertSalaryUseCase

    @Inject
    lateinit var updateSalaryUseCase: UpdateSalaryUseCase

    @Inject
    lateinit var getSalaryUseCase: GetSalaryUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)

        viewMvc = MainActivityViewMvcImpl(layoutInflater, null)
        setContentView(viewMvc.getRootView())

        setSalary()
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewMvc.unregisterListener(this)
    }

    private fun setSalary() {
        coroutineScope.launch {
            if (getSalaryUseCase.getSalary() != null) {
                viewMvc.setSalary(getSalaryUseCase.getSalary())
            }
        }
    }

    override fun inputSalary(salaryEntity: SalaryEntity, salaryIsNull: Boolean) {
        coroutineScope.launch {
            if (salaryIsNull) {
                insertSalaryUseCase.insertSalary(salaryEntity)
            } else {
                updateSalaryUseCase.updateSalary(salaryEntity)
            }
        }

        setSalary()
    }

    override fun addSalary(salary: Int) {
        val customDialog = CustomDialog("Input Salary", salary.toString())

        customDialog.show(supportFragmentManager, "inputSalary")
    }
}