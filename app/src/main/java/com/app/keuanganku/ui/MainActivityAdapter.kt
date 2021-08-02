package com.app.keuanganku.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.keuanganku.data.entity.AllocationItem
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.helper.AllocationDiffCallback
import com.app.keuanganku.data.helper.CurrencyFormatterIDR
import com.app.keuanganku.databinding.ItemSalaryAllocationBinding
import com.app.keuanganku.ui.MainActivityAdapter.MainActivityViewHolder

class MainActivityAdapter internal constructor(
    private val activity: Activity,
    private val onClickButtonItem: OnClickButtonItem,
) :
    RecyclerView.Adapter<MainActivityViewHolder>() {
    private val listSalaryAllocations = ArrayList<SalaryAllocation>()

    private val currencyFormatterIDR: CurrencyFormatterIDR = CurrencyFormatterIDR()

    private var totalAllocationAmount = 0

    private val listAllocationItem = ArrayList<AllocationItem>()

    fun setListSalaryAllocations(listAllocation: List<SalaryAllocation>) {
        val diffCallback = AllocationDiffCallback(this.listSalaryAllocations, listAllocation)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.listSalaryAllocations.clear()
        this.listSalaryAllocations.addAll(listAllocation)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setAllocationTotalAmount(amount: Int) {
        this.totalAllocationAmount = amount
    }

    fun setAllocationItemList(allocationItemList: List<AllocationItem>) {
        this.listAllocationItem.clear()
        this.listAllocationItem.addAll(allocationItemList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
        val binding =
            ItemSalaryAllocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainActivityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
        holder.bind(listSalaryAllocations[position])
    }

    override fun getItemCount(): Int {
        return listSalaryAllocations.size
    }

    inner class MainActivityViewHolder(private val binding: ItemSalaryAllocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(salaryAllocation: SalaryAllocation) {
            with(binding) {
                tvItemAllocationName.text = salaryAllocation.title
                tvItemAllocationAmount.text = salaryAllocation.amount?.let {
                    currencyFormatterIDR.getCurrency(
                        it
                    )
                }
                tvAllocationTotalSpend.text = totalAllocationAmount.toString()
                btnAddAllocationItem.setOnClickListener {
                    onClickButtonItem.onButtonOnClick()
                }

                val adapter = AllocationItemAdapter(activity)

                rvItemAllocation.layoutManager = LinearLayoutManager(activity.applicationContext)
                rvItemAllocation.setHasFixedSize(true)
                rvItemAllocation.adapter = adapter
            }
        }
    }
}

interface OnClickButtonItem {
    fun onButtonOnClick()

}