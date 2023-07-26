package com.app.keuanganku.ui.main.salaryallocationitem

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.app.keuanganku.R
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.helper.CurrencyFormatterIDR
import com.app.keuanganku.ui.common.viewmvc.BaseObservableViewMvc

class SalaryAllocationItemViewMvcImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
) : BaseObservableViewMvc<SalaryAllocationItemViewMvc.Listener>(), SalaryAllocationItemViewMvc {

    private val salaryAllocationTitleTextView: TextView
    private val salaryAllocationAmount: TextView
    private val salaryAllocationTotalAmount: TextView
    private val buttonAddAllocationItem: Button

    private lateinit var salaryAllocation: SalaryAllocation
    private val currencyFormatterIDR: CurrencyFormatterIDR = CurrencyFormatterIDR()

    init {
        setRootView(layoutInflater.inflate(R.layout.item_salary_allocation, parent, false))

        salaryAllocationTitleTextView = findViewById(R.id.tv_item_allocation_name)
        salaryAllocationAmount = findViewById(R.id.tv_item_allocation_amount)
        salaryAllocationTotalAmount = findViewById(R.id.tv_allocation_total_spend)
        buttonAddAllocationItem = findViewById(R.id.btn_add_allocation_item)

        getRootView().setOnLongClickListener {
            for (listener in getListeners()) {
                listener.onItemLongClick(salaryAllocation)
            }
            true
        }

        buttonAddAllocationItem.setOnClickListener {
            for (listener in getListeners()) {
                listener.onItemClicked(salaryAllocation)
            }
        }
    }

    override fun bindSalaryAllocation(salaryAllocation: SalaryAllocation) {
        this.salaryAllocation = salaryAllocation

        salaryAllocationTitleTextView.text = salaryAllocation.title
        salaryAllocationAmount.text = salaryAllocation.amount?.let {
            currencyFormatterIDR.getCurrency(
                it
            )
        }
    }

}