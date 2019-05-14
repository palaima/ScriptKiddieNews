package io.palaima.android.scriptkiddienews.ui.feed.data

import io.palaima.android.scriptkiddienews.ui.base.ViewAffect

sealed class FeedViewAffect : ViewAffect {
    data class ShowToast(val message: String): FeedViewAffect()
}