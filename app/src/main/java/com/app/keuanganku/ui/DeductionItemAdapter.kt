package com.app.keuanganku.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.keuanganku.data.entity.DeductionItem
import com.app.keuanganku.ui.DeductionItemAdapter.AllocationItemViewHolder
import com.app.keuanganku.ui.common.ViewMvcFactory
import com.app.keuanganku.ui.main.deductionitem.DeductionItemViewMvc

class DeductionItemAdapter(val viewMvcFactory: ViewMvcFactory) :
    RecyclerView.Adapter<AllocationItemViewHolder>() {

    private val listDeductionItems = ArrayList<DeductionItem>()

    fun setListAllocationItem(listAllDeductionItem: List<DeductionItem>) {
        this.listDeductionItems.clear()
        this.listDeductionItems.addAll(listAllDeductionItem)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllocationItemViewHolder {
        val viewMvc = viewMvcFactory.getAllocationItemViewMvc(parent)
        return AllocationItemViewHolder(viewMvc)
    }

    override fun onBindViewHolder(holder: AllocationItemViewHolder, position: Int) {
        holder.viewMvc.bindItem(listDeductionItems[position])
    }

    override fun getItemCount(): Int = listDeductionItems.size

    inner class AllocationItemViewHolder(val viewMvc: DeductionItemViewMvc) :
        RecyclerView.ViewHolder(viewMvc.getRootView()) {

    }
}