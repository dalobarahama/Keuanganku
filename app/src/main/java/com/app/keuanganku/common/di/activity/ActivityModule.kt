package com.app.keuanganku.common.di.activity

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: AppCompatActivity) {

    @Provides
    fun activity(): AppCompatActivity = activity

}