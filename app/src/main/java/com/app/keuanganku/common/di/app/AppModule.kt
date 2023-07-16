package com.app.keuanganku.common.di.app

import android.app.Application
import com.app.keuanganku.data.room.DatabaseKeuanganku
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: Application) {

    @Provides
    fun application() = application

    @Provides
    @AppScope
    fun localDatabase(application: Application): DatabaseKeuanganku =
        DatabaseKeuanganku.getInstance(application.applicationContext)
}