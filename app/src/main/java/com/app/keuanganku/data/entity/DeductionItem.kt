package com.app.keuanganku.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "allocation_item")
@Parcelize
data class DeductionItem(

    @PrimaryKey(autoGenerate = true)
    val allocationItemId: Int? = null,

    @ColumnInfo(name = "allocation_item_title")
    var title: String? = null,

    @ColumnInfo(name = "allocation_item_amount")
    var amount: Int? = null

) : Parcelable