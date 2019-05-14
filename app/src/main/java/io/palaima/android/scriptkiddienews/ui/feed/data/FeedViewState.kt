package io.palaima.android.scriptkiddienews.ui.feed.data

import io.palaima.android.scriptkiddienews.ui.base.ViewState

sealed class FeedViewState : ViewState {
    data class ShowFeed(val items: List<FeedItem>) : FeedViewState()
}