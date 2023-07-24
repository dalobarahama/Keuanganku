package com.app.keuanganku.usecase.salary

import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.data.room.KeuangankuDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSalaryUseCase(private val keuangankuDao: KeuangankuDao) {

    suspend fun getSalary(): SalaryEntity {
        return withContext(Dispatchers.IO) {
            keuangankuDao.getSalary()
        }
    }

}