package com.app.keuanganku.common.di.app

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: Application) {

    @Provides
    fun application() = application
}