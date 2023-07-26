package com.app.keuanganku.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.keuanganku.R
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.data.helper.CurrencyFormatterIDR
import com.app.keuanganku.ui.common.ViewMvcFactory
import com.app.keuanganku.ui.common.viewmvc.BaseObservableViewMvc

class MainActivityViewMvcImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    viewMvcFactory: ViewMvcFactory,
) :
    BaseObservableViewMvc<MainActivityViewMvc.Listener>(),
    MainActivityViewMvc, MainActivityAdapter.Listener {

    private val mainActivityAdapter: MainActivityAdapter
    private val currencyFormatterIDR: CurrencyFormatterIDR = CurrencyFormatterIDR()

    private val tvSalary: TextView

    init {
        setRootView(layoutInflater.inflate(R.layout.activity_main, parent, false))

        mainActivityAdapter = MainActivityAdapter(this, viewMvcFactory)

        tvSalary = findViewById(R.id.tv_salary)
        val btnAddSalary = findViewById<Button>(R.id.btn_add_salary)
        val btnAddSalaryAllocation = findViewById<Button>(R.id.btn_add_salary_allocation)
        val rvAllocation = findViewById<RecyclerView>(R.id.rv_allocation)

        btnAddSalary.setOnClickListener {
            for (listener in getListeners()) {
                listener.inputSalary()
            }
        }

        btnAddSalaryAllocation.setOnClickListener {
            for (listener in getListeners()) {
                listener.addSalaryAllocation()
            }
        }

        rvAllocation.layoutManager = LinearLayoutManager(getContext())
        rvAllocation.setHasFixedSize(true)
        rvAllocation.adapter = mainActivityAdapter
    }

    override fun setSalary(salaryEntity: SalaryEntity) {
        tvSalary.text = "Salary ${
            salaryEntity.salary?.let {
                currencyFormatterIDR.getCurrency(
                    it
                )
            }
        }"
    }

    override fun bindSalaryAllocation(salaryAllocations: List<SalaryAllocation>) {
        mainActivityAdapter.setListSalaryAllocations(salaryAllocations)
    }

    override fun onItemClicked(salaryAllocation: SalaryAllocation) {
        for (listener in getListeners()) {
            listener.addSalaryAllocationItem(salaryAllocation)
        }
    }

    override fun onItemLongClick(salaryAllocation: SalaryAllocation) {
        for (listener in getListeners()){
            listener.onSalaryAllocationLongClicked(salaryAllocation)
        }
    }
}