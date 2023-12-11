package com.app.keuanganku.data.helper

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.keuanganku.data.entity.DeductionItem

class TotalAllocation {

    private var totalAllocation: MutableLiveData<Int> = MutableLiveData<Int>()

    private var allocationList = ArrayList<DeductionItem>()

    private fun calculateTotal() {
        var total = 0
        for (element in allocationList) {
            total += element.amount!!
        }
        Log.i("TotalAllocation", "salary total: $totalAllocation")
        totalAllocation.value = total
    }

    fun setAllocationList(allocationList: List<DeductionItem>) {
        this.allocationList.clear()
        this.allocationList.addAll(allocationList)
        calculateTotal()
    }

    fun getTotalAllocation(): LiveData<Int> {
        Log.i("TotalAllocation", "getTotalAllocation: $totalAllocation")
        return totalAllocation
    }
}