package com.app.keuanganku.ui

import android.app.Dialog
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.keuanganku.R
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.data.helper.CurrencyFormatterIDR

class MainActivityViewMvcImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    OnClickButtonItem {

    interface Listener {
        fun inputSalary(salaryEntity: SalaryEntity, salaryIsNull: Boolean)
        fun addSalary(salary: Int)
    }

    private val listeners = HashSet<Listener>()

    private val rootView: View = layoutInflater.inflate(R.layout.activity_main, parent, false)

    private var salaryIsNull = true
    private lateinit var salaryEntityFromDB: SalaryEntity
    private val mainActivityAdapter: MainActivityAdapter
    private val allocationItemAdapter: AllocationItemAdapter
    private val currencyFormatterIDR: CurrencyFormatterIDR = CurrencyFormatterIDR()

    init {
        mainActivityAdapter = MainActivityAdapter(getRootView().context, this)
        allocationItemAdapter = AllocationItemAdapter()

        val btnAddSalary = findViewById<Button>(R.id.btn_add_salary)
        val btnAddSalaryAllocation = findViewById<Button>(R.id.btn_add_salary_allocation)
        val rvAllocation = findViewById<RecyclerView>(R.id.rv_allocation)

        btnAddSalary.setOnClickListener {
//            showDialogInputSalary()
            for (listener in listeners) {
                if (!salaryIsNull) {
                    salaryEntityFromDB.salary?.let { it1 -> listener.addSalary(it1) }
                } else {
                    listener.addSalary(0)
                }

            }
        }

        btnAddSalaryAllocation.setOnClickListener {
//            showDialogInputSalaryAllocation()
        }

        rvAllocation.layoutManager = LinearLayoutManager(getRootView().context)
        rvAllocation.setHasFixedSize(true)
        rvAllocation.adapter = mainActivityAdapter
    }

    fun getRootView(): View {
        return rootView
    }

    private fun <T : View?> findViewById(id: Int): T {
        return getRootView().findViewById<T>(id)
    }

    fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    fun setSalary(salaryEntity: SalaryEntity) {
        val tvSalary = findViewById<TextView>(R.id.tv_salary)

        salaryIsNull = false
        tvSalary.text = "Salary ${
            salaryEntity.salary?.let {
                currencyFormatterIDR.getCurrency(
                    it
                )
            }
        }"
        salaryEntityFromDB = salaryEntity
    }

    override fun onButtonOnClick() {
        Toast.makeText(getRootView().context, "Clicked", Toast.LENGTH_SHORT)
            .show()
    }

    private fun showDialogInputSalary() {
        val dialog = Dialog(getRootView().context)
        dialog.setContentView(R.layout.dialog_layout)

        val editTextInputSalary = dialog.findViewById<EditText>(R.id.et_dialog_first_input)
        val textViewTitle = dialog.findViewById<TextView>(R.id.tv_dialog_title)
        val buttonCancel = dialog.findViewById<Button>(R.id.btn_dialog_negative)
        val buttonSave = dialog.findViewById<Button>(R.id.btn_dialog_positive)

        editTextInputSalary.hint = "1000000"
        editTextInputSalary.inputType = InputType.TYPE_CLASS_NUMBER
        if (!salaryIsNull) {
            salaryEntityFromDB.salary.toString().let { editTextInputSalary.setText(it) }
        }

        textViewTitle.text = "Input Salary"

        buttonSave.setOnClickListener {
            val salaryEntity: SalaryEntity

            if (salaryIsNull) {
                salaryEntity = SalaryEntity(null, editTextInputSalary.text.toString().toInt())
            } else {
                salaryEntity = salaryEntityFromDB
                salaryEntity.salary = editTextInputSalary.text.toString().toInt()
            }

            for (listener in listeners) {
                listener.inputSalary(salaryEntity, salaryIsNull)
            }

            dialog.dismiss()
        }

        buttonCancel.setOnClickListener {
            dialog.cancel()
        }

        dialog.show()
    }
}