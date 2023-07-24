package com.app.keuanganku.usecase

import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.room.KeuangankuDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSalaryAllocationUseCase(private val keuangankuDao: KeuangankuDao) {

    suspend fun getSalaryAllocation(): List<SalaryAllocation> {
        return withContext(Dispatchers.IO) {
            keuangankuDao.getAllSalaryAllocation()
        }
    }

}