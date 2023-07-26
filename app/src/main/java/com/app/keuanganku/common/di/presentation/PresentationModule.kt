package com.app.keuanganku.common.di.presentation

import com.app.keuanganku.data.room.KeuangankuDao
import com.app.keuanganku.usecase.allocationitem.GetAllocationItemUseCase
import com.app.keuanganku.usecase.allocationitem.InsertAllocationItemUseCase
import com.app.keuanganku.usecase.allocationitem.UpdateAllocationItemUseCase
import com.app.keuanganku.usecase.salary.GetSalaryUseCase
import com.app.keuanganku.usecase.salary.UpdateSalaryUseCase
import com.app.keuanganku.usecase.salaryallocation.GetSalaryAllocationUseCase
import com.app.keuanganku.usecase.salaryallocation.InsertSalaryAllocationUseCase
import com.app.keuanganku.usecase.salaryallocation.UpdateSalaryAllocationUseCase
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun updateSalaryUseCase(keuangankuDao: KeuangankuDao) = UpdateSalaryUseCase(keuangankuDao)

    @Provides
    fun getSalaryUseCase(keuangankuDao: KeuangankuDao) = GetSalaryUseCase(keuangankuDao)

    @Provides
    fun getSalaryAllocationUseCase(keuangankuDao: KeuangankuDao) =
        GetSalaryAllocationUseCase(keuangankuDao)

    @Provides
    fun insertSalaryAllocationUseCase(keuangankuDao: KeuangankuDao) =
        InsertSalaryAllocationUseCase(keuangankuDao)

    @Provides
    fun updateSalaryAllocationUseCase(keuangankuDao: KeuangankuDao) =
        UpdateSalaryAllocationUseCase(keuangankuDao)

    @Provides
    fun getAllocationItemUseCase(keuangankuDao: KeuangankuDao) =
        GetAllocationItemUseCase(keuangankuDao)

    @Provides
    fun insertAllocationItemUseCase(keuangankuDao: KeuangankuDao) =
        InsertAllocationItemUseCase(keuangankuDao)

    @Provides
    fun updateAllocationItemUseCase(keuangankuDao: KeuangankuDao) =
        UpdateAllocationItemUseCase(keuangankuDao)
}