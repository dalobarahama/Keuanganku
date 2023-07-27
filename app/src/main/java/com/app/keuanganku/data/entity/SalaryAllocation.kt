package com.app.keuanganku.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "salary_allocation")
@Parcelize
data class SalaryAllocation(

    @PrimaryKey(autoGenerate = true)
    val salaryAllocationId: Int? = null,

    @ColumnInfo(name = "allocation_title")
    var title: String? = null,

    @ColumnInfo(name = "allocation_amount")
    var amount: Int? = null

) : Parcelable
