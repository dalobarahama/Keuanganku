package com.app.keuanganku.common.di.presentation

import com.app.keuanganku.ui.MainActivity
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(activity: MainActivity)
}