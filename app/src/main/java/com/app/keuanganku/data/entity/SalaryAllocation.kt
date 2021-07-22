package com.app.keuanganku.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "salary_allocation")
data class SalaryAllocation(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "allocation_title")
    val title: String,

    @ColumnInfo(name = "allocation_amount")
    val amount: Int

)
