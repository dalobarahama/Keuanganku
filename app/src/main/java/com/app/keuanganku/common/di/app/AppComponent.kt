package com.app.keuanganku.common.di.app

import com.app.keuanganku.common.di.activity.ActivityComponent
import com.app.keuanganku.common.di.activity.ActivityModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent
}