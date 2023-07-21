package com.app.keuanganku.ui.common.dialog

class CustomDialogEvent(private val clickedButton: Button) {

    enum class Button {
        POSITIVE,
        NEGATIVE
    }

    fun getClickedButton(): Button {
        return clickedButton
    }

}