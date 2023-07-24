package com.app.keuanganku.usecase

import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.room.KeuangankuDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InsertSalaryAllocationUseCase(private val keuangankuDao: KeuangankuDao) {

    suspend fun insertSalary(salaryAllocation: SalaryAllocation) {
        withContext(Dispatchers.IO) {
            keuangankuDao.insertSalaryAllocation(salaryAllocation)
        }
    }
}