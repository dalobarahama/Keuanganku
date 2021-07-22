package com.app.keuanganku.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.entity.SalaryEntity

@Dao
interface KeuangankuDao {

    @Insert
    fun insertSalary(salary: SalaryEntity)

    @Update
    fun updateSalary(salary: SalaryEntity)

    @Query("SELECT * FROM salary_entity")
    fun getSalary(): LiveData<SalaryEntity>

    @Insert
    fun insertSalaryAllocation(salaryAllocation: SalaryAllocation)

    @Update
    fun updateSalaryAllocation(salaryAllocation: SalaryAllocation)

    @Query("SELECT * FROM salary_allocation")
    fun getAllSalaryAllocation(): LiveData<List<SalaryAllocation>>
}