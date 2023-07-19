package com.app.keuanganku.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.app.keuanganku.data.entity.AllocationItem
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.data.room.DatabaseKeuanganku
import com.app.keuanganku.data.room.KeuangankuDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repository(application: Application) {
    private val keuangankuDao: KeuangankuDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val databaseKeuanganku = DatabaseKeuanganku.getInstance(application)
        keuangankuDao = databaseKeuanganku.dao()
    }

//    fun getSalary(): LiveData<SalaryEntity> = keuangankuDao.getSalary()

    fun getAllSalaryAllocation(): LiveData<List<SalaryAllocation>> =
        keuangankuDao.getAllSalaryAllocation()

    fun getAllAllocationItem(): LiveData<List<AllocationItem>> =
        keuangankuDao.getAllSAllocationItem()

    fun insertSalary(salary: SalaryEntity) {
        executorService.execute { keuangankuDao.insertSalary(salary) }
    }

    fun updateSalary(salary: SalaryEntity) {
        executorService.execute { keuangankuDao.updateSalary(salary) }
    }

    fun insertSalaryAllocation(salaryAllocation: SalaryAllocation) {
        executorService.execute { keuangankuDao.insertSalaryAllocation(salaryAllocation) }
    }

    fun updateSalaryAllocation(salaryAllocation: SalaryAllocation) {
        executorService.execute { keuangankuDao.updateSalaryAllocation(salaryAllocation) }
    }

    fun insertAllocationItem(allocationItem: AllocationItem) {
        executorService.execute { keuangankuDao.insertAllocationItem(allocationItem) }
    }

    fun updateAllocationItem(allocationItem: AllocationItem) {
        executorService.execute { keuangankuDao.updateAllocationItem(allocationItem) }
    }
}