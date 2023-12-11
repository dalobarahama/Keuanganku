package com.app.keuanganku.usecase.allocationitem

import com.app.keuanganku.data.entity.DeductionItem
import com.app.keuanganku.data.room.KeuangankuDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InsertAllocationItemUseCase(private val keuangankuDao: KeuangankuDao) {

    suspend fun insertAllocation(deductionItem: DeductionItem) {
        withContext(Dispatchers.IO) {
            keuangankuDao.insertAllocationItem(deductionItem)
        }
    }
}