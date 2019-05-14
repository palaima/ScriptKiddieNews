package io.palaima.android.scriptkiddienews.ui.base

import androidx.lifecycle.LiveData

interface ViewAffectProvider {

    fun onViewAffect(): LiveData<ViewAffect>
}