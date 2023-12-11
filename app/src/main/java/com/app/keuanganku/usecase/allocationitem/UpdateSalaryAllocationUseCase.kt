package com.app.keuanganku.usecase.allocationitem

import com.app.keuanganku.data.entity.DeductionItem
import com.app.keuanganku.data.room.KeuangankuDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateAllocationItemUseCase(val keuangankuDao: KeuangankuDao) {

    suspend fun updateSalaryAllocation(deductionItem: DeductionItem) {
        withContext(Dispatchers.IO) {
            keuangankuDao.updateAllocationItem(deductionItem)
        }
    }
}