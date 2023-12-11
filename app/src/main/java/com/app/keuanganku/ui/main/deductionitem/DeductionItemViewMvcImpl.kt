package com.app.keuanganku.ui.main.deductionitem

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.app.keuanganku.R
import com.app.keuanganku.data.entity.DeductionItem
import com.app.keuanganku.ui.common.viewmvc.BaseObservableViewMvc

class DeductionItemViewMvcImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseObservableViewMvc<DeductionItemViewMvc.Listener>(),
    DeductionItemViewMvc {

    private val tvItemName: TextView
    private val tvItemAmount: TextView

    init {
        setRootView(layoutInflater.inflate(R.layout.item_allocation, parent, false))

        tvItemName = findViewById(R.id.tv_item_allocation_name)
        tvItemAmount = findViewById(R.id.tv_item_allocation_amount)
    }

    override fun bindItem(deductionItem: DeductionItem) {
        tvItemName.text = deductionItem.title
        tvItemAmount.text = deductionItem.amount.toString()
    }
}