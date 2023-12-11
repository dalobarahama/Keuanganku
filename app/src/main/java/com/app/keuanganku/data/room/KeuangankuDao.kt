package com.app.keuanganku.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.app.keuanganku.data.entity.DeductionItem
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.entity.SalaryEntity

@Dao
interface KeuangankuDao {

    @Insert
    fun insertSalary(salary: SalaryEntity)

    @Update
    fun updateSalary(salary: SalaryEntity)

    @Query("SELECT * FROM salary_entity")
    fun getSalary(): SalaryEntity

    @Insert
    fun insertSalaryAllocation(salaryAllocation: SalaryAllocation)

    @Update
    fun updateSalaryAllocation(salaryAllocation: SalaryAllocation)

    @Query("SELECT * FROM salary_allocation")
    fun getAllSalaryAllocation(): List<SalaryAllocation>

    @Insert
    fun insertAllocationItem(deductionItem: DeductionItem)

    @Update
    fun updateAllocationItem(deductionItem: DeductionItem)

    @Query("SELECT * FROM allocation_item")
    fun getAllSAllocationItem(): List<DeductionItem>
}