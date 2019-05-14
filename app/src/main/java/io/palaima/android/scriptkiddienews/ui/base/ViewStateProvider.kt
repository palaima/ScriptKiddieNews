package io.palaima.android.scriptkiddienews.ui.base

import androidx.lifecycle.LiveData

interface ViewStateProvider {

    fun onViewState(): LiveData<ViewState>
}