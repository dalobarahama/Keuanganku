package com.app.keuanganku.ui.common.dialog

import com.app.keuanganku.data.entity.SalaryEntity

class CustomDialogEvent(
    private val clickedButton: Button,
    private val salaryEntity: SalaryEntity? = null,
) {

    enum class Button {
        POSITIVE,
        NEGATIVE
    }

    fun getClickedButton(): Button {
        return clickedButton
    }

    fun getSalaryEntity(): SalaryEntity? {
        return salaryEntity
    }

}