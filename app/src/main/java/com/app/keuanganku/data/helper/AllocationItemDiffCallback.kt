package com.app.keuanganku.data.helper

import androidx.recyclerview.widget.DiffUtil
import com.app.keuanganku.data.entity.AllocationItem

class AllocationItemDiffCallback(
    private val oldList: List<AllocationItem>,
    private val newList: List<AllocationItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].allocationItemId == newList[newItemPosition].allocationItemId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = oldList[oldItemPosition]
        val newEmployee = newList[newItemPosition]
        return oldEmployee.title == newEmployee.title && oldEmployee.amount == newEmployee.amount
    }

}