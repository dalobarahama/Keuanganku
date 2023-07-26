package com.app.keuanganku.usecase.allocationitem

import com.app.keuanganku.data.entity.AllocationItem
import com.app.keuanganku.data.room.KeuangankuDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllocationItemUseCase(private val keuangankuDao: KeuangankuDao) {

    suspend fun getSalaryAllocation(): List<AllocationItem> {
        return withContext(Dispatchers.IO) {
            keuangankuDao.getAllSAllocationItem()
        }
    }

}