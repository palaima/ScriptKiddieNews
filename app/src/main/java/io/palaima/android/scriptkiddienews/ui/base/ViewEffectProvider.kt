package io.palaima.android.scriptkiddienews.ui.base

import androidx.lifecycle.LiveData

interface ViewEffectProvider {

    fun onViewEffect(): LiveData<ViewEffect>
}