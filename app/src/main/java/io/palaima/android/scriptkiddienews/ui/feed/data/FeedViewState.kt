package io.palaima.android.scriptkiddienews.ui.feed.data

import io.palaima.android.scriptkiddienews.ui.base.ViewState

sealed class FeedViewState : ViewState {
    object Loading : FeedViewState()
    object Error : FeedViewState()
    data class ShowFeed(val items: List<FeedItem>) : FeedViewState()
}