package com.app.keuanganku.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "allocation_item")
@Parcelize
data class AllocationItem(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,

    @ColumnInfo(name = "allocation_item_title")
    val title: String? = null,

    @ColumnInfo(name = "allocation_item_amount")
    val amount: Int? = null

) : Parcelable