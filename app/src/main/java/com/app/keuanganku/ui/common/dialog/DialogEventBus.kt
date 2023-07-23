package com.app.keuanganku.ui.common.dialog

import com.app.keuanganku.ui.common.BaseObservableViewMvc

class DialogEventBus : BaseObservableViewMvc<DialogEventBus.Listener>() {

    interface Listener {
        fun onDialogEvent(event: Any)
    }

    fun postEvent(event: Any) {
        for (listener in getListeners()) {
            listener.onDialogEvent(event)
        }
    }

}