package com.app.keuanganku.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.keuanganku.data.Repository
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.entity.SalaryEntity

class KeuangankuViewModel(application: Application) : ViewModel() {
    private val repository: Repository = Repository(application)

    fun insertSalary(salary: SalaryEntity) {
        repository.insertSalary(salary)
    }

    fun updateSalary(salary: SalaryEntity) {
        repository.updateSalary(salary)
    }

    fun getSalary(): LiveData<SalaryEntity> = repository.getSalary()

    fun insertSalaryAllocation(salaryAllocation: SalaryAllocation) {
        repository.insertSalaryAllocation(salaryAllocation)
    }

    fun updateSalaryAllocation(salaryAllocation: SalaryAllocation) {
        repository.updateSalaryAllocation(salaryAllocation)
    }

    fun getAllSalaryAllocation(): LiveData<List<SalaryAllocation>> =
        repository.getAllSalaryAllocation()
}