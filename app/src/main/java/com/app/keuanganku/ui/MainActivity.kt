package com.app.keuanganku.ui

import android.app.Dialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.keuanganku.R
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.data.helper.CurrencyFormatterIDR
import com.app.keuanganku.databinding.ActivityMainBinding
import com.app.keuanganku.viewmodel.ViewModelFactory
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding

    private lateinit var keuangankuViewModel: KeuangankuViewModel

    private var salaryIsNull = true
    private lateinit var salaryEntityFromDB: SalaryEntity

    private lateinit var adapter: MainActivityAdapter

    private val currencyFormatterIDR: CurrencyFormatterIDR = CurrencyFormatterIDR()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        adapter = MainActivityAdapter(this)

        keuangankuViewModel = obtainViewModel(this)
        keuangankuViewModel.getSalary().observe(this, salaryObserver)
        keuangankuViewModel.getAllSalaryAllocation().observe(this, salaryAllocationObserver)

        binding?.btnAddSalary?.setOnClickListener {
            showDialogInputSalary()
        }

        binding?.btnAddSalaryAllocation?.setOnClickListener {
            showDialogInputSalaryAllocation()
        }

        binding?.rvAllocation?.layoutManager = LinearLayoutManager(this)
        binding?.rvAllocation?.setHasFixedSize(true)
        binding?.rvAllocation?.adapter = adapter
    }

    private fun obtainViewModel(activity: AppCompatActivity): KeuangankuViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(KeuangankuViewModel::class.java)
    }

    private val salaryObserver = Observer<SalaryEntity> { salary ->
        if (salary != null) {
            salaryIsNull = false
            ("Salary " + salary.salary?.let { currencyFormatterIDR.getCurrency(it) }).also {
                binding?.tvSalary!!.text = it
            }
            salaryEntityFromDB = salary
        }
    }

    private val salaryAllocationObserver = Observer<List<SalaryAllocation>> { salaryAllocation ->
        if (salaryAllocation != null) {
            salaryAllocation[0].title?.let { Log.i("SalaryAllocation ", it) }

            adapter.setListSalaryAllocations(salaryAllocation)
        }
    }

    private fun showDialogInputSalary() {
        val dialog = Dialog(this)
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
            saveSalary(editTextInputSalary.text.toString())
            dialog.dismiss()
        }

        buttonCancel.setOnClickListener {
            dialog.cancel()
        }

        dialog.show()
    }

    private fun showDialogInputSalaryAllocation() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_layout)

        val editTextInputAllocationTitle = dialog.findViewById<EditText>(R.id.et_dialog_first_input)
        val editTextInputAllocationAmount =
            dialog.findViewById<EditText>(R.id.et_dialog_second_input)
        val textViewTitle = dialog.findViewById<TextView>(R.id.tv_dialog_title)
        val buttonCancel = dialog.findViewById<Button>(R.id.btn_dialog_negative)
        val buttonSave = dialog.findViewById<Button>(R.id.btn_dialog_positive)

        textViewTitle.text = "Input Salary Allocation"

        editTextInputAllocationTitle.hint = "Title"
        editTextInputAllocationTitle.inputType = InputType.TYPE_CLASS_TEXT

        editTextInputAllocationAmount.visibility = View.VISIBLE
        editTextInputAllocationAmount.hint = "Amount"
        editTextInputAllocationAmount.inputType = InputType.TYPE_CLASS_NUMBER

        buttonSave.setOnClickListener {
            val salaryAllocation = SalaryAllocation(
                null,
                editTextInputAllocationTitle.text.toString(),
                Integer.parseInt(editTextInputAllocationAmount.text.toString())
            )
            saveSalaryAllocation(salaryAllocation)
            dialog.dismiss()
        }

        buttonCancel.setOnClickListener {
            dialog.cancel()
        }

        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }

    private fun saveSalary(salary: String) {
        val salaryEntity: SalaryEntity

        if (salaryIsNull) {
            salaryEntity = SalaryEntity(null, salary.toInt())
            keuangankuViewModel.insertSalary(salaryEntity)
        } else {
            salaryEntityFromDB.salary = salary.toInt()
            keuangankuViewModel.updateSalary(salaryEntityFromDB)
        }
    }

    private fun saveSalaryAllocation(salaryAllocation: SalaryAllocation) {
        keuangankuViewModel.insertSalaryAllocation(salaryAllocation)
    }
}