package com.app.keuanganku.ui.common.dialog.addsalary

import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.app.keuanganku.R
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialogViewMvc
import com.app.keuanganku.ui.common.viewmvc.BaseObservableViewMvc

class DialogAddSalaryViewMvcImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
) : BaseObservableViewMvc<BaseCustomDialogViewMvc.Listener>(), DialogAddSalaryViewMvc {

    private var textViewTitle: TextView
    private var editTextInputSalary: EditText
    private lateinit var salaryEntity: SalaryEntity

    init {
        setRootView(layoutInflater.inflate(R.layout.dialog_layout, parent))

        editTextInputSalary = findViewById(R.id.et_dialog_first_input)
        textViewTitle = findViewById(R.id.tv_dialog_title)
        val buttonCancel = findViewById<Button>(R.id.btn_dialog_negative)
        val buttonSave = findViewById<Button>(R.id.btn_dialog_positive)

        editTextInputSalary.hint = "1000000"
        editTextInputSalary.inputType = InputType.TYPE_CLASS_NUMBER

        buttonSave.setOnClickListener {
            salaryEntity.salary = editTextInputSalary.text.toString().toInt()

            for (listener in getListeners()) {
                listener.onClickPositiveButton(salaryEntity)
            }
        }

        buttonCancel.setOnClickListener {
            for (listener in getListeners()) {
                listener.onClickNegativeButton()
            }
        }

    }

    override fun setTitle(title: String) {
        textViewTitle.text = title
    }

    override fun setEditTextSalary(salary: String) {
        editTextInputSalary.setText(salary)
    }

    override fun setSalaryEntity(salaryEntity: SalaryEntity) {
        this.salaryEntity = salaryEntity
    }
}