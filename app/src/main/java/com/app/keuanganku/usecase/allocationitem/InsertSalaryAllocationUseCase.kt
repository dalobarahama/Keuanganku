package com.app.keuanganku.usecase.allocationitem

import com.app.keuanganku.data.entity.AllocationItem
import com.app.keuanganku.data.room.KeuangankuDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InsertAllocationItemUseCase(private val keuangankuDao: KeuangankuDao) {

    suspend fun insertAllocation(allocationItem: AllocationItem) {
        withContext(Dispatchers.IO) {
            keuangankuDao.insertAllocationItem(allocationItem)
        }
    }
}