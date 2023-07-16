package com.app.keuanganku.common.di.activity

import com.app.keuanganku.common.di.presentation.PresentationComponent
import com.app.keuanganku.common.di.presentation.PresentationModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}