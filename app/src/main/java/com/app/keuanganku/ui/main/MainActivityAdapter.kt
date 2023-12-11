package com.app.keuanganku.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.ui.common.ViewMvcFactory
import com.app.keuanganku.ui.main.MainActivityAdapter.MainActivityViewHolder
import com.app.keuanganku.ui.main.salaryallocationitem.SalaryAllocationItemViewMvc

class MainActivityAdapter(
    val listener: Listener,
    val viewMvcFactory: ViewMvcFactory,
) :
    RecyclerView.Adapter<MainActivityViewHolder>(), SalaryAllocationItemViewMvc.Listener {

    interface Listener {
        fun onItemClicked(salaryAllocation: SalaryAllocation)
        fun onItemLongClick(salaryAllocation: SalaryAllocation)
    }

    private val listSalaryAllocations = ArrayList<SalaryAllocation>()

    private var totalAllocationAmount = 0

    fun setListSalaryAllocations(listAllocation: List<SalaryAllocation>) {
        this.listSalaryAllocations.clear()
        this.listSalaryAllocations.addAll(listAllocation)

        notifyDataSetChanged()
    }

    fun setAllocationTotalAmount(amount: Int) {
        this.totalAllocationAmount = amount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
        val viewMvc = viewMvcFactory.getSalaryAllocationItemViewMvc(parent)
        viewMvc.registerListener(this)
        return MainActivityViewHolder(viewMvc)
    }

    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
        holder.viewMvc.bindSalaryAllocation(listSalaryAllocations[position])
//        holder.viewMvc.bindDeductionItem(listSalaryAllocations[position])
    }

    override fun getItemCount(): Int {
        return listSalaryAllocations.size
    }

    inner class MainActivityViewHolder(val viewMvc: SalaryAllocationItemViewMvc) :
        RecyclerView.ViewHolder(viewMvc.getRootView())

    override fun onItemClicked(salaryAllocation: SalaryAllocation) {
        listener.onItemClicked(salaryAllocation)
    }

    override fun onItemLongClick(salaryAllocation: SalaryAllocation) {
        listener.onItemLongClick(salaryAllocation)
    }
}