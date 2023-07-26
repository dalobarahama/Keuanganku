package com.app.keuanganku.ui.main.allocationitem

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.app.keuanganku.R
import com.app.keuanganku.data.entity.AllocationItem
import com.app.keuanganku.ui.common.viewmvc.BaseObservableViewMvc

class AllocationItemViewMvcImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseObservableViewMvc<AllocationItemViewMvc.Listener>(),
    AllocationItemViewMvc {

    private val tvItemName: TextView
    private val tvItemAmount: TextView

    init {
        setRootView(layoutInflater.inflate(R.layout.item_allocation, parent, false))

        tvItemName = findViewById(R.id.tv_item_allocation_name)
        tvItemAmount = findViewById(R.id.tv_item_allocation_amount)
    }

    override fun bindItem(allocationItem: AllocationItem) {
        tvItemName.text = allocationItem.title
        tvItemAmount.text = allocationItem.amount.toString()
    }
}