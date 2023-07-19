package com.app.keuanganku.usecase

import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.data.room.KeuangankuDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateSalaryUseCase(private val keuangankuDao: KeuangankuDao) {

    suspend fun updateSalary(salaryEntity: SalaryEntity){
        withContext(Dispatchers.IO) {
            keuangankuDao.updateSalary(salaryEntity)
        }
    }

}