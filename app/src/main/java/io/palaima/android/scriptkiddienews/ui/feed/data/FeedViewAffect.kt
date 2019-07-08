package io.palaima.android.scriptkiddienews.ui.feed.data

import io.palaima.android.scriptkiddienews.ui.base.ViewEffect

sealed class FeedViewAffect : ViewEffect {
    data class ShowToast(val message: String): FeedViewAffect()
}