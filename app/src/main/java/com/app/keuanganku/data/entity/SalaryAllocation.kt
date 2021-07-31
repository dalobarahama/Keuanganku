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
    val id: Int? = 0,

    @ColumnInfo(name = "allocation_title")
    val title: String? = null,

    @ColumnInfo(name = "allocation_amount")
    val amount: Int? = null

) : Parcelable
