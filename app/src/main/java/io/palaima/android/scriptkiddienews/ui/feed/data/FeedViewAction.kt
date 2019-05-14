package io.palaima.android.scriptkiddienews.ui.feed.data

import io.palaima.android.scriptkiddienews.ui.base.ViewAction

sealed class FeedViewAction : ViewAction {
    object RefreshFeed : FeedViewAction()
}