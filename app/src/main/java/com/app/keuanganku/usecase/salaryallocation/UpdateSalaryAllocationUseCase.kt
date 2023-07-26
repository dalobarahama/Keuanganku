package com.app.keuanganku.usecase.salaryallocation

import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.room.KeuangankuDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateSalaryAllocationUseCase(val keuangankuDao: KeuangankuDao) {

    suspend fun updateSalaryAllocation(salaryAllocation: SalaryAllocation) {
        withContext(Dispatchers.IO) {
            keuangankuDao.updateSalaryAllocation(salaryAllocation)
        }
    }
}