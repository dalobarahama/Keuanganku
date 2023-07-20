package com.app.keuanganku.common.di.presentation

import com.app.keuanganku.ui.CustomDialog
import com.app.keuanganku.ui.MainActivity
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(activity: MainActivity)
    fun inject(dialog: CustomDialog)
}