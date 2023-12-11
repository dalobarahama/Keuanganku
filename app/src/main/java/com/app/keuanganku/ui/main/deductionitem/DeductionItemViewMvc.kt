package com.app.keuanganku.ui.main.deductionitem

import com.app.keuanganku.data.entity.DeductionItem
import com.app.keuanganku.ui.common.viewmvc.ObservableViewMvc

interface DeductionItemViewMvc: ObservableViewMvc<DeductionItemViewMvc.Listener> {

    interface Listener{
        fun onItemClicked()
    }

    fun bindItem(deductionItem: DeductionItem)

}