package com.app.keuanganku.ui.main.salaryallocationitem

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.keuanganku.R
import com.app.keuanganku.data.entity.DeductionItem
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.helper.CurrencyFormatterIDR
import com.app.keuanganku.ui.DeductionItemAdapter
import com.app.keuanganku.ui.common.ViewMvcFactory
import com.app.keuanganku.ui.common.viewmvc.BaseObservableViewMvc

class SalaryAllocationItemViewMvcImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    viewMvcFactory: ViewMvcFactory,
) : BaseObservableViewMvc<SalaryAllocationItemViewMvc.Listener>(), SalaryAllocationItemViewMvc {

    private val salaryAllocationTitleTextView: TextView
    private val salaryAllocationAmount: TextView
    private val salaryAllocationTotalAmount: TextView
    private val buttonAddAllocationItem: Button

    private lateinit var salaryAllocation: SalaryAllocation
    private val currencyFormatterIDR: CurrencyFormatterIDR = CurrencyFormatterIDR()

    private val deductionItemAdapter: DeductionItemAdapter

    init {
        setRootView(layoutInflater.inflate(R.layout.item_salary_allocation, parent, false))

        deductionItemAdapter = DeductionItemAdapter(viewMvcFactory)

        salaryAllocationTitleTextView = findViewById(R.id.tv_item_allocation_name)
        salaryAllocationAmount = findViewById(R.id.tv_item_allocation_amount)
        salaryAllocationTotalAmount = findViewById(R.id.tv_allocation_total_spend)
        buttonAddAllocationItem = findViewById(R.id.btn_add_allocation_item)
        val recyclerViewAllocationItem = findViewById<RecyclerView>(R.id.rv_item_allocation)

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

        recyclerViewAllocationItem.layoutManager = LinearLayoutManager(getContext())
        recyclerViewAllocationItem.setHasFixedSize(true)
        recyclerViewAllocationItem.adapter = deductionItemAdapter
    }

    override fun bindDeductionItem(deductionItems: List<DeductionItem>) {
        deductionItemAdapter.setListAllocationItem(deductionItems)
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