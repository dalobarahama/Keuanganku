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
import com.app.keuanganku.ui.common.BaseObservableViewMvc

class MainActivityViewMvcImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseObservableViewMvc<MainActivityViewMvc.Listener>(),
    MainActivityViewMvc,
    OnClickButtonItem {

    private val mainActivityAdapter: MainActivityAdapter
    private val allocationItemAdapter: AllocationItemAdapter
    private val currencyFormatterIDR: CurrencyFormatterIDR = CurrencyFormatterIDR()

    init {
        setRootView(layoutInflater.inflate(R.layout.activity_main, parent, false))

        mainActivityAdapter = MainActivityAdapter(getContext(), this)
        allocationItemAdapter = AllocationItemAdapter()

        val btnAddSalary = findViewById<Button>(R.id.btn_add_salary)
        val btnAddSalaryAllocation = findViewById<Button>(R.id.btn_add_salary_allocation)
        val rvAllocation = findViewById<RecyclerView>(R.id.rv_allocation)

        btnAddSalary.setOnClickListener {
            for (listener in getListeners()) {
                listener.inputSalary()
            }
        }

        btnAddSalaryAllocation.setOnClickListener {

        }

        rvAllocation.layoutManager = LinearLayoutManager(getContext())
        rvAllocation.setHasFixedSize(true)
        rvAllocation.adapter = mainActivityAdapter
    }

    fun setSalary(salaryEntity: SalaryEntity) {
        val tvSalary = findViewById<TextView>(R.id.tv_salary)

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