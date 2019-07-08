package io.palaima.android.scriptkiddienews.ui.feed.data

import io.reactivex.Observable
import java.util.*

class FeedRepository {

    fun fetchFeed(): Observable<List<FeedItem>> {
        val items = (0..Random().nextInt(20)).asIterable().map { it.toString() }.map { FeedItem(it) }.toList()
        return Observable.just(items)
    }
}