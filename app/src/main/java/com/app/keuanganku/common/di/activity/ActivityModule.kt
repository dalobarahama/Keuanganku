package com.app.keuanganku.common.di.activity

import androidx.appcompat.app.AppCompatActivity
import com.app.keuanganku.common.di.app.AppScope
import com.app.keuanganku.ui.common.ViewMvcFactory
import com.app.keuanganku.ui.common.dialog.DialogEventBus
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: AppCompatActivity) {

    @Provides
    fun activity(): AppCompatActivity = activity

    @Provides
    @ActivityScope
    fun viewMvcFactory() = ViewMvcFactory(activity.layoutInflater)
}