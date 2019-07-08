package io.palaima.android.scriptkiddienews.ui.feed.data

import io.palaima.android.scriptkiddienews.ui.base.ViewEvent

sealed class FeedViewEvent : ViewEvent {
    object RefreshFeed : FeedViewEvent()
}