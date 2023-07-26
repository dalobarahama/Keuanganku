package com.app.keuanganku.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.keuanganku.data.entity.AllocationItem
import com.app.keuanganku.data.helper.CurrencyFormatterIDR
import com.app.keuanganku.ui.AllocationItemAdapter.AllocationItemViewHolder
import com.app.keuanganku.ui.common.ViewMvcFactory
import com.app.keuanganku.ui.main.allocationitem.AllocationItemViewMvc

class AllocationItemAdapter(val viewMvcFactory: ViewMvcFactory) :
    RecyclerView.Adapter<AllocationItemViewHolder>() {

    private val listAllocationItems = ArrayList<AllocationItem>()

    fun setListAllocationItem(listAllAllocationItem: List<AllocationItem>) {
        this.listAllocationItems.clear()
        this.listAllocationItems.addAll(listAllAllocationItem)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllocationItemViewHolder {
        val viewMvc = viewMvcFactory.getAllocationItemViewMvc(parent)
        return AllocationItemViewHolder(viewMvc)
    }

    override fun onBindViewHolder(holder: AllocationItemViewHolder, position: Int) {
        holder.viewMvc.bindItem(listAllocationItems[position])
    }

    override fun getItemCount(): Int = listAllocationItems.size

    inner class AllocationItemViewHolder(val viewMvc: AllocationItemViewMvc) :
        RecyclerView.ViewHolder(viewMvc.getRootView()) {

    }
}