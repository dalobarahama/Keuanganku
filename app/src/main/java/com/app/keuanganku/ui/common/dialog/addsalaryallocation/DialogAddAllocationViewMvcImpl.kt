package com.app.keuanganku.ui.common.dialog.addsalaryallocation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.app.keuanganku.R
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialogViewMvc
import com.app.keuanganku.ui.common.viewmvc.BaseObservableViewMvc

class DialogAddAllocationViewMvcImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseObservableViewMvc<BaseCustomDialogViewMvc.Listener>(),
    DialogAddAllocationViewMvc {

    private val title: TextView
    private val editTextAllocationAmount: EditText
    private val editTextAllocationTitle: EditText

    init {
        setRootView(layoutInflater.inflate(R.layout.dialog_layout, parent))

        title = findViewById(R.id.tv_dialog_title)
        editTextAllocationTitle = findViewById(R.id.et_dialog_first_input)
        editTextAllocationAmount = findViewById(R.id.et_dialog_second_input)
        editTextAllocationAmount.visibility = View.VISIBLE

        val buttonCancel = findViewById<Button>(R.id.btn_dialog_negative)
        val buttonSave = findViewById<Button>(R.id.btn_dialog_positive)

        buttonSave.setOnClickListener {
            val salaryAllocation = SalaryAllocation(
                null,
                editTextAllocationTitle.text.toString(),
                editTextAllocationAmount.text.toString().toInt()
            )

            for (listener in getListeners()) {
                listener.onClickPositiveButton(salaryAllocation)
            }
        }

        buttonCancel.setOnClickListener {
            for (listener in getListeners()) {
                listener.onClickNegativeButton()
            }
        }

    }

    override fun setAllocationTitle(title: String) {
        editTextAllocationTitle.setText(title)
    }

    override fun setAllocationAmount(amount: Int) {
        editTextAllocationAmount.setText(amount.toString())
    }

    override fun setTitle(title: String) {
        this.title.text = title
    }
}