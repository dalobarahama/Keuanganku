package com.app.keuanganku.ui.common.viewmvc

interface ObservableViewMvc<ListenerType> : ViewMvc {

    fun registerListener(listener: ListenerType)

    fun unregisterListener(listener: ListenerType)

}