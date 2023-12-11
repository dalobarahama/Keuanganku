package com.app.keuanganku.data.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class DeductionItemInSalaryAllocation(
    @Embedded
    val salaryAllocation: SalaryAllocation,
    @Relation(
        parentColumn = "salaryAllocationId",
        entityColumn = "allocationItemId",
        associateBy = Junction(SalaryAllocationWithDeductionItem::class)
    )
    val deductionItems: List<DeductionItem>,
)
