package com.app.keuanganku.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.keuanganku.R
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.data.helper.CurrencyFormatterIDR
import com.app.keuanganku.ui.AllocationItemAdapter
import com.app.keuanganku.ui.common.viewmvc.BaseObservableViewMvc

class MainActivityViewMvcImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseObservableViewMvc<MainActivityViewMvc.Listener>(),
    MainActivityViewMvc,
    OnClickButtonItem {

    private val mainActivityAdapter: MainActivityAdapter
    private val allocationItemAdapter: AllocationItemAdapter
    private val currencyFormatterIDR: CurrencyFormatterIDR = CurrencyFormatterIDR()

    private val tvSalary: TextView

    init {
        setRootView(layoutInflater.inflate(R.layout.activity_main, parent, false))

        mainActivityAdapter = MainActivityAdapter(getContext(), this)
        allocationItemAdapter = AllocationItemAdapter()

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
                listener.onSalaryAllocationClicked()
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

    override fun onButtonOnClick() {
        Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT)
            .show()
    }
}