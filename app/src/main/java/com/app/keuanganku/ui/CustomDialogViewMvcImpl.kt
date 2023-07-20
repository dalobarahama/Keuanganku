package com.app.keuanganku.ui

import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.app.keuanganku.R
import com.app.keuanganku.data.entity.SalaryEntity

class CustomDialogViewMvcImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
) {

    interface Listener {
        fun onClickPositiveButton(salaryEntity: SalaryEntity, salaryIsNull: Boolean)
        fun onClickNegativeButton()
    }

    val rootView = layoutInflater.inflate(R.layout.dialog_layout, parent, false)

    private val listeners = HashSet<Listener>()

    private var textViewTitle: TextView
    private var editTextInputSalary: EditText
    private var salaryIsNull = true
    private lateinit var salaryEntityFromDB: SalaryEntity

    init {

        editTextInputSalary = findViewById(R.id.et_dialog_first_input)
        textViewTitle = findViewById(R.id.tv_dialog_title)
        val buttonCancel = findViewById<Button>(R.id.btn_dialog_negative)
        val buttonSave = findViewById<Button>(R.id.btn_dialog_positive)

        editTextInputSalary.hint = "1000000"
        editTextInputSalary.inputType = InputType.TYPE_CLASS_NUMBER

        buttonSave.setOnClickListener {
            val salaryEntity: SalaryEntity

            if (salaryIsNull) {
                salaryEntity = SalaryEntity(null, editTextInputSalary.text.toString().toInt())
            } else {
                salaryEntity = salaryEntityFromDB
                salaryEntity.salary = editTextInputSalary.text.toString().toInt()
            }

            for (listener in listeners) {
                listener.onClickPositiveButton(salaryEntity, salaryIsNull)
            }
        }

        buttonCancel.setOnClickListener {
            for (listener in listeners) {
                listener.onClickNegativeButton()
            }
        }

    }

    private fun <T : View?> findViewById(id: Int): T {
        return rootView.findViewById<T>(id)
    }

    fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    fun setTitle(title: String) {
        textViewTitle.text = title
    }

    fun setEditTextSalary(salary: String) {
        editTextInputSalary.setText(salary)
    }

    fun setSalaryIsNull(salaryIsNull: Boolean) {
        this.salaryIsNull = salaryIsNull
    }
}