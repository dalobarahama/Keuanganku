package com.app.keuanganku.common.di.presentation

import com.app.keuanganku.ui.common.dialog.addsalary.DialogAddSalary
import com.app.keuanganku.ui.common.dialog.addsalaryallocation.DialogAddAllocation
import com.app.keuanganku.ui.main.MainActivity
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(activity: MainActivity)
    fun inject(dialog: DialogAddSalary)
    fun inject(dialog: DialogAddAllocation)
}