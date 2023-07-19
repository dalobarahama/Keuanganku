package com.app.keuanganku.ui

import android.os.Bundle
import com.app.keuanganku.data.entity.SalaryEntity
import com.app.keuanganku.ui.common.BaseActivity
import com.app.keuanganku.usecase.GetSalaryUseCase
import com.app.keuanganku.usecase.InsertSalaryUseCase
import com.app.keuanganku.usecase.UpdateSalaryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityViewMvcImpl.Listener {

    private lateinit var viewMvc: MainActivityViewMvcImpl

    @Inject
    lateinit var insertSalaryUseCase: InsertSalaryUseCase

    @Inject
    lateinit var updateSalaryUseCase: UpdateSalaryUseCase

    @Inject
    lateinit var getSalaryUseCase: GetSalaryUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)

        viewMvc = MainActivityViewMvcImpl(layoutInflater, null)
        setContentView(viewMvc.getRootView())

        setSalary()
    }

//    private fun showDialogInputSalary() {
//        val dialog = Dialog(this)
//        dialog.setContentView(R.layout.dialog_layout)
//
//        val editTextInputSalary = dialog.findViewById<EditText>(R.id.et_dialog_first_input)
//        val textViewTitle = dialog.findViewById<TextView>(R.id.tv_dialog_title)
//        val buttonCancel = dialog.findViewById<Button>(R.id.btn_dialog_negative)
//        val buttonSave = dialog.findViewById<Button>(R.id.btn_dialog_positive)
//
//        editTextInputSalary.hint = "1000000"
//        editTextInputSalary.inputType = InputType.TYPE_CLASS_NUMBER
//        if (!salaryIsNull) {
//            salaryEntityFromDB.salary.toString().let { editTextInputSalary.setText(it) }
//        }
//
//        textViewTitle.text = "Input Salary"
//
//        buttonSave.setOnClickListener {
//            saveSalary(editTextInputSalary.text.toString())
//            dialog.dismiss()
//        }
//
//        buttonCancel.setOnClickListener {
//            dialog.cancel()
//        }
//
//        dialog.show()
//    }

//    private fun showDialogInputSalaryAllocation() {
//        val dialog = Dialog(this)
//        dialog.setContentView(R.layout.dialog_layout)
//
//        val editTextInputAllocationTitle = dialog.findViewById<EditText>(R.id.et_dialog_first_input)
//        val editTextInputAllocationAmount =
//            dialog.findViewById<EditText>(R.id.et_dialog_second_input)
//        val textViewTitle = dialog.findViewById<TextView>(R.id.tv_dialog_title)
//        val buttonCancel = dialog.findViewById<Button>(R.id.btn_dialog_negative)
//        val buttonSave = dialog.findViewById<Button>(R.id.btn_dialog_positive)
//
//        textViewTitle.text = "Input Salary Allocation"
//
//        editTextInputAllocationTitle.hint = "Title"
//        editTextInputAllocationTitle.inputType = InputType.TYPE_CLASS_TEXT
//
//        editTextInputAllocationAmount.visibility = View.VISIBLE
//        editTextInputAllocationAmount.hint = "Amount"
//        editTextInputAllocationAmount.inputType = InputType.TYPE_CLASS_NUMBER
//
//        buttonSave.setOnClickListener {
//            val salaryAllocation = SalaryAllocation(
//                null,
//                editTextInputAllocationTitle.text.toString(),
//                Integer.parseInt(editTextInputAllocationAmount.text.toString())
//            )
//            saveSalaryAllocation(salaryAllocation)
//            dialog.dismiss()
//        }
//
//        buttonCancel.setOnClickListener {
//            dialog.cancel()
//        }
//
//        dialog.show()
//    }
//
//    private fun showDialogInputAllocationItem() {
//        val dialog = Dialog(this)
//        dialog.setContentView(R.layout.dialog_layout)
//
//        val editTextInputAllocationTitle = dialog.findViewById<EditText>(R.id.et_dialog_first_input)
//        val editTextInputAllocationAmount =
//            dialog.findViewById<EditText>(R.id.et_dialog_second_input)
//        val textViewTitle = dialog.findViewById<TextView>(R.id.tv_dialog_title)
//        val buttonCancel = dialog.findViewById<Button>(R.id.btn_dialog_negative)
//        val buttonSave = dialog.findViewById<Button>(R.id.btn_dialog_positive)
//
//        textViewTitle.text = "Input Salary Allocation"
//
//        editTextInputAllocationTitle.hint = "Title"
//        editTextInputAllocationTitle.inputType = InputType.TYPE_CLASS_TEXT
//
//        editTextInputAllocationAmount.visibility = View.VISIBLE
//        editTextInputAllocationAmount.hint = "Amount"
//        editTextInputAllocationAmount.inputType = InputType.TYPE_CLASS_NUMBER
//
//        buttonSave.setOnClickListener {
//            val allocationItem = AllocationItem(
//                null,
//                editTextInputAllocationTitle.text.toString(),
//                Integer.parseInt(editTextInputAllocationAmount.text.toString())
//            )
//            saveAllocationItem(allocationItem)
//            dialog.dismiss()
//        }
//
//        buttonCancel.setOnClickListener {
//            dialog.cancel()
//        }
//
//        dialog.show()
//    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewMvc.unregisterListener(this)
    }

    private fun setSalary(){
        coroutineScope.launch {
            if (getSalaryUseCase.getSalary() != null) {
                viewMvc.setSalary(getSalaryUseCase.getSalary())
            }
        }
    }

//    private fun saveSalaryAllocation(salaryAllocation: SalaryAllocation) {
//        keuangankuViewModel.insertSalaryAllocation(salaryAllocation)
//    }
//
//    private fun saveAllocationItem(allocationItem: AllocationItem) {
//        keuangankuViewModel.insertAllocationItem(allocationItem)
//    }

    override fun inputSalary(salaryEntity: SalaryEntity, salaryIsNull: Boolean) {
        coroutineScope.launch {
            if (salaryIsNull) {
                insertSalaryUseCase.insertSalary(salaryEntity)
            } else {
                updateSalaryUseCase.updateSalary(salaryEntity)
            }
        }

        setSalary()
    }
}