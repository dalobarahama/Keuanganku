package com.app.keuanganku.usecase

import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.data.room.KeuangankuDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InsertSalaryUseCase(private val keuangankuDao: KeuangankuDao) {

    suspend fun insertSalary(salaryEntity: SalaryEntity) {
        withContext(Dispatchers.IO) {
            keuangankuDao.insertSalary(salaryEntity)
        }
    }
}