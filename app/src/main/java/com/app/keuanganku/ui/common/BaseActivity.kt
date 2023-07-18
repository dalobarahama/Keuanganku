package com.app.keuanganku.ui.common

import androidx.appcompat.app.AppCompatActivity
import com.app.keuanganku.MyApp
import com.app.keuanganku.common.di.activity.ActivityModule
import com.app.keuanganku.common.di.presentation.PresentationModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class BaseActivity : AppCompatActivity() {

    val activityComponent by lazy {
        (application as MyApp).appComponent.newActivityComponent(ActivityModule(this))
    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent(PresentationModule())
    }

    val injector get() = presentationComponent

    val coroutineScope get() = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
}