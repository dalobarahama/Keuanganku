package com.app.keuanganku

import android.app.Application
import com.app.keuanganku.common.di.app.AppModule
import com.app.keuanganku.common.di.app.DaggerAppComponent

class MyApp: Application() {

    val appComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }

}