package com.app.keuanganku.common.di.app

import android.app.Application
import com.app.keuanganku.data.room.DatabaseKeuanganku
import com.app.keuanganku.ui.common.dialog.DialogEventBus
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

    @Provides
    @AppScope
    fun keuangankuDao(localDatabase: DatabaseKeuanganku) = localDatabase.dao()

    @Provides
    @AppScope
    fun dialogEventBus() = DialogEventBus()
}