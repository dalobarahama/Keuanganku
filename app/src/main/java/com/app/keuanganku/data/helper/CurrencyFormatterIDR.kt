package com.app.keuanganku.data.helper

import java.text.NumberFormat
import java.util.*

class CurrencyFormatterIDR {
    private val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance()

    fun getCurrency(value: Int): String? {
        currencyFormat.maximumFractionDigits = 0
        currencyFormat.currency = Currency.getInstance("IDR")

        return currencyFormat.format(value)
    }
}