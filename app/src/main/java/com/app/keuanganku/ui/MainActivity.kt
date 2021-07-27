package com.app.keuanganku.ui

import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.databinding.ActivityMainBinding
import com.app.keuanganku.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding

    private lateinit var keuangankuViewModel: KeuangankuViewModel

    private var salaryIsNull = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        keuangankuViewModel = obtainViewModel(this)
        keuangankuViewModel.getSalary().observe(this, salaryObserver)

        binding?.btnAddSalary?.setOnClickListener {
            showAlertDialog()
        }

        binding?.btnAddSalaryAllocation?.setOnClickListener {
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): KeuangankuViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(KeuangankuViewModel::class.java)
    }

    private val salaryObserver = Observer<SalaryEntity> { salary ->
        if (salary != null) {
            salaryIsNull = false
            ("Salary Rp " + salary.salary.toString()).also { binding?.tvSalary!!.text = it }
        }
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)

        val editTextInput = EditText(this)
        editTextInput.hint = "1000000"
        editTextInput.inputType = InputType.TYPE_CLASS_NUMBER

        builder.setTitle("Input Salary")
        builder.setView(editTextInput)

        builder.setPositiveButton("Save") { _, _ ->
            val salary = editTextInput.text.toString()
            val salaryEntity = SalaryEntity(1, salary.toInt())
            if (salaryIsNull) {
                keuangankuViewModel.insertSalary(salaryEntity)
            } else {
                keuangankuViewModel.updateSalary(salaryEntity)
            }
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }
}