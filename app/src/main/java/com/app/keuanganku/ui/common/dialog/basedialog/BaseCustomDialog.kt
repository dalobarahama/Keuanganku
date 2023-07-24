package com.app.keuanganku.ui.common.dialog.basedialog

import androidx.fragment.app.DialogFragment
import com.app.keuanganku.common.di.presentation.PresentationModule
import com.app.keuanganku.ui.common.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class BaseCustomDialog : DialogFragment() {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(
            PresentationModule()
        )
    }

    val injector get() = presentationComponent

    val coroutineScope get() = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
}