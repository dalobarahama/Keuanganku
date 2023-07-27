package com.app.keuanganku.data.helper

import androidx.recyclerview.widget.DiffUtil
import com.app.keuanganku.data.entity.SalaryAllocation

class AllocationDiffCallback(
    private val oldList: List<SalaryAllocation>,
    private val newList: List<SalaryAllocation>
) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].salaryAllocationId == newList[newItemPosition].salaryAllocationId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = oldList[oldItemPosition]
        val newEmployee = newList[newItemPosition]
        return oldEmployee.title == newEmployee.title && oldEmployee.amount == newEmployee.amount
    }
}