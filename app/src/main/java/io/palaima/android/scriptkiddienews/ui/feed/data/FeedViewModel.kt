package io.palaima.android.scriptkiddienews.ui.feed.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.palaima.android.scriptkiddienews.ui.base.*
import java.lang.IllegalStateException

class FeedViewModel : ViewModel(), ViewActionObserver, ViewStateProvider, ViewAffectProvider {

    private val state = MutableLiveData<ViewState>()
    private val affect = MutableLiveData<ViewAffect>()

    override fun onViewState(): LiveData<ViewState> {
        return state
    }

    override fun onViewAffect(): LiveData<ViewAffect> {
        return affect
    }

    override fun postViewAction(action: ViewAction) {
        when (action) {
            FeedViewAction.RefreshFeed -> {
                affect.value = FeedViewAffect.ShowToast("Hello kiddies")
                state.value = FeedViewState.ShowFeed(listOf())
            }
            else -> {
                throw IllegalStateException("Action $action is not supported")
            }
        }
    }

}