package com.app.keuanganku.usecase.allocationitem

import com.app.keuanganku.data.entity.AllocationItem
import com.app.keuanganku.data.room.KeuangankuDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateAllocationItemUseCase(val keuangankuDao: KeuangankuDao) {

    suspend fun updateSalaryAllocation(allocationItem: AllocationItem) {
        withContext(Dispatchers.IO) {
            keuangankuDao.updateAllocationItem(allocationItem)
        }
    }
}