package com.app.keuanganku.ui.main.allocationitem

import com.app.keuanganku.data.entity.AllocationItem
import com.app.keuanganku.ui.common.viewmvc.ObservableViewMvc

interface AllocationItemViewMvc: ObservableViewMvc<AllocationItemViewMvc.Listener> {

    interface Listener{
        fun onItemClicked()
    }

    fun bindItem(allocationItem: AllocationItem)

}