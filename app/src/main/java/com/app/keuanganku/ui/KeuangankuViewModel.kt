package com.app.keuanganku.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.keuanganku.data.Repository
import com.app.keuanganku.data.entity.AllocationItem
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.data.helper.TotalAllocation

class KeuangankuViewModel(application: Application) : ViewModel() {
    private val repository: Repository = Repository(application)

    private val totalAllocation: TotalAllocation = TotalAllocation()

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

    fun insertAllocationItem(allocationItem: AllocationItem) {
        repository.insertAllocationItem(allocationItem)
    }

    fun updateAllocationItem(allocationItem: AllocationItem) {
        repository.updateAllocationItem(allocationItem)
    }

    fun getAllAllocationItem(): LiveData<List<AllocationItem>> = repository.getAllAllocationItem()

    fun setAllocationList(allocationList: List<AllocationItem>) {
        totalAllocation.setAllocationList(allocationList)
    }

    fun getTotalAllocation(): LiveData<Int> = totalAllocation.getTotalAllocation()
}