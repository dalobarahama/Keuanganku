package com.app.keuanganku.ui.common.dialog.addallocationitem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.app.keuanganku.R
import com.app.keuanganku.data.entity.DeductionItem
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialogViewMvc
import com.app.keuanganku.ui.common.viewmvc.BaseObservableViewMvc

class DialogAddDeductionItemViewMvcImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseObservableViewMvc<BaseCustomDialogViewMvc.Listener>(),
    DialogAddDeductionItemViewMvc {

    private val title: TextView
    private val editTextAllocationItemAmount: EditText
    private val editTextAllocationItemTitle: EditText

    private var deductionItem: DeductionItem = DeductionItem()

    init {
        setRootView(layoutInflater.inflate(R.layout.dialog_layout, parent))

        title = findViewById(R.id.tv_dialog_title)
        editTextAllocationItemTitle = findViewById(R.id.et_dialog_first_input)
        editTextAllocationItemAmount = findViewById(R.id.et_dialog_second_input)
        editTextAllocationItemAmount.visibility = View.VISIBLE

        val buttonCancel = findViewById<Button>(R.id.btn_dialog_negative)
        val buttonSave = findViewById<Button>(R.id.btn_dialog_positive)

        buttonSave.setOnClickListener {
            deductionItem.title = editTextAllocationItemTitle.text.toString()
            deductionItem.amount = editTextAllocationItemAmount.text.toString().toInt()

            for (listener in getListeners()) {
                listener.onClickPositiveButton(deductionItem)
            }
        }

        buttonCancel.setOnClickListener {
            for (listener in getListeners()) {
                listener.onClickNegativeButton()
            }
        }

    }

    private fun setAllocationTitle(title: String) {
        editTextAllocationItemTitle.setText(title)
    }

    private fun setAllocationAmount(amount: Int) {
        editTextAllocationItemAmount.setText(amount.toString())
    }

    override fun setSalaryAllocationItem(deductionItem: DeductionItem?) {
        if (deductionItem != null) {
            this.deductionItem = deductionItem
            setAllocationTitle(deductionItem.title.toString())
            deductionItem.amount?.let { setAllocationAmount(it) }
        }
    }

    override fun setTitle(title: String) {
        this.title.text = title
    }
}