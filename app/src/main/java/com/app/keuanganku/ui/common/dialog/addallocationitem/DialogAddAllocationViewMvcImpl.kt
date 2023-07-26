package com.app.keuanganku.ui.common.dialog.addallocationitem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.app.keuanganku.R
import com.app.keuanganku.data.entity.AllocationItem
import com.app.keuanganku.ui.common.dialog.basedialog.BaseCustomDialogViewMvc
import com.app.keuanganku.ui.common.viewmvc.BaseObservableViewMvc

class DialogAddAllocationItemViewMvcImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseObservableViewMvc<BaseCustomDialogViewMvc.Listener>(),
    DialogAddAllocationItemViewMvc {

    private val title: TextView
    private val editTextAllocationItemAmount: EditText
    private val editTextAllocationItemTitle: EditText

    private var allocationItem: AllocationItem = AllocationItem()

    init {
        setRootView(layoutInflater.inflate(R.layout.dialog_layout, parent))

        title = findViewById(R.id.tv_dialog_title)
        editTextAllocationItemTitle = findViewById(R.id.et_dialog_first_input)
        editTextAllocationItemAmount = findViewById(R.id.et_dialog_second_input)
        editTextAllocationItemAmount.visibility = View.VISIBLE

        val buttonCancel = findViewById<Button>(R.id.btn_dialog_negative)
        val buttonSave = findViewById<Button>(R.id.btn_dialog_positive)

        buttonSave.setOnClickListener {
            allocationItem.title = editTextAllocationItemTitle.text.toString()
            allocationItem.amount = editTextAllocationItemAmount.text.toString().toInt()

            for (listener in getListeners()) {
                listener.onClickPositiveButton(allocationItem)
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

    override fun setSalaryAllocationItem(allocationItem: AllocationItem?) {
        if (allocationItem != null) {
            this.allocationItem = allocationItem
            setAllocationTitle(allocationItem.title.toString())
            allocationItem.amount?.let { setAllocationAmount(it) }
        }
    }

    override fun setTitle(title: String) {
        this.title.text = title
    }
}