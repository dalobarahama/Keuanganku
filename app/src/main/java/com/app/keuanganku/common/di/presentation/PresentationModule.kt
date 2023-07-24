package com.app.keuanganku.common.di.presentation

import com.app.keuanganku.data.room.KeuangankuDao
import com.app.keuanganku.usecase.salaryallocation.GetSalaryAllocationUseCase
import com.app.keuanganku.usecase.salary.GetSalaryUseCase
import com.app.keuanganku.usecase.salaryallocation.InsertSalaryAllocationUseCase
import com.app.keuanganku.usecase.salary.UpdateSalaryUseCase
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun insertSalaryAllocationUseCase(keuangankuDao: KeuangankuDao) =
        InsertSalaryAllocationUseCase(keuangankuDao)

    @Provides
    fun updateSalaryUseCase(keuangankuDao: KeuangankuDao) = UpdateSalaryUseCase(keuangankuDao)

    @Provides
    fun getSalaryUseCase(keuangankuDao: KeuangankuDao) = GetSalaryUseCase(keuangankuDao)

    @Provides
    fun getSalaryAllocationUseCase(keuangankuDao: KeuangankuDao) =
        GetSalaryAllocationUseCase(keuangankuDao)
}