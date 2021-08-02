package com.app.keuanganku.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.keuanganku.data.entity.AllocationItem
import com.app.keuanganku.data.helper.AllocationItemDiffCallback
import com.app.keuanganku.data.helper.CurrencyFormatterIDR
import com.app.keuanganku.databinding.ItemAllocationBinding
import com.app.keuanganku.ui.AllocationItemAdapter.AllocationItemViewHolder

class AllocationItemAdapter internal constructor(
    private val activity: Activity
) : RecyclerView.Adapter<AllocationItemViewHolder>() {
    private val currencyFormatterIDR: CurrencyFormatterIDR = CurrencyFormatterIDR()

    private val listAllocationItems = ArrayList<AllocationItem>()

    fun setListAllocationItem(listAllAllocationItem: List<AllocationItem>) {
        val diffCallback =
            AllocationItemDiffCallback(this.listAllocationItems, listAllAllocationItem)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.listAllocationItems.clear()
        this.listAllocationItems.addAll(listAllAllocationItem)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllocationItemViewHolder {
        val binding =
            ItemAllocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllocationItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllocationItemViewHolder, position: Int) {
        holder.bind(listAllocationItems[position])
    }

    override fun getItemCount(): Int = listAllocationItems.size

    class AllocationItemViewHolder(private val binding: ItemAllocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(allocationItem: AllocationItem) {
            with(binding) {
                tvItemName.text = allocationItem.title
                tvItemAmount.text = allocationItem.amount.toString()
            }
        }
    }
}