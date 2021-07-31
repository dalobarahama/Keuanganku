package com.app.keuanganku.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.helper.AllocationDiffCallback
import com.app.keuanganku.data.helper.CurrencyFormatterIDR
import com.app.keuanganku.databinding.ItemSalaryAllocationBinding
import com.app.keuanganku.ui.MainActivityAdapter.MainActivityViewHolder

class MainActivityAdapter internal constructor(private val activity: Activity) :
    RecyclerView.Adapter<MainActivityViewHolder>() {
    private val listSalaryAllocations = ArrayList<SalaryAllocation>()

    private val currencyFormatterIDR: CurrencyFormatterIDR = CurrencyFormatterIDR()

    fun setListSalaryAllocations(listAllocation: List<SalaryAllocation>) {
        val diffCallback = AllocationDiffCallback(this.listSalaryAllocations, listAllocation)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.listSalaryAllocations.clear()
        this.listSalaryAllocations.addAll(listAllocation)
        diffResult.dispatchUpdatesTo(this)
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
                btnAddAllocationItem.setOnClickListener {
                    Toast.makeText(activity.applicationContext, "Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
