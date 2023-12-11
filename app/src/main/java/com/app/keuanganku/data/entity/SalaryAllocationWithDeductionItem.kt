package com.app.keuanganku.data.entity

import androidx.room.Entity

@Entity(primaryKeys = ["salaryAllocationId", "allocationItemId"])
data class SalaryAllocationWithDeductionItem(
    val salaryAllocationId: Int,
    val allocationItemId: Int,
)
