package io.palaima.android.scriptkiddienews.ui.feed.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.palaima.android.scriptkiddienews.ui.base.*
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.lang.IllegalStateException

class FeedViewModel : ViewModel(), ViewEventObserver, ViewStateProvider, ViewEffectProvider {

    private val repository = FeedRepository()
    private val defaultState = FeedViewState.ShowFeed(listOf())
    private val state = MutableLiveData<ViewState>()
    private val effect = MutableLiveData<ViewEffect>()
    private val eventSubject = PublishSubject.create<ViewEvent>()

    private var disposable: Disposable? = null

    init {
        disposable = eventSubject
            .publish { o ->
                Observable.merge(
                    o.ofType(FeedViewEvent.RefreshFeed::class.java).compose(refreshStateReducer()),
                    o.ofType(FeedViewEvent.RefreshFeed::class.java).compose(refreshEffectReducer())
                )
            }
            .subscribe({
                when (it) {
                    is ViewState -> {
                        state.postValue(it)
                    }
                    is ViewEffect -> {
                        effect.postValue(it)
                    }
                    else -> {
                        throw IllegalStateException("")
                    }
                }
            }, {

            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    override fun onViewState(): LiveData<ViewState> {
        return state
    }

    override fun onViewEffect(): LiveData<ViewEffect> {
        return effect
    }

    override fun processViewEvent(event: ViewEvent) {
        eventSubject.onNext(event)
    }

    private fun refreshStateReducer(): ObservableTransformer<FeedViewEvent.RefreshFeed, FeedViewState.ShowFeed> {
        return ObservableTransformer {
            it
                .switchMap {
                    repository.fetchFeed()
                }
                .subscribeOn(Schedulers.io())
                .scan(defaultState, { state, result ->
                    when (state) {
                        is FeedViewState.ShowFeed -> {
                            state.copy(items = result)
                        }
                        else -> {
                            throw IllegalStateException("")
                        }
                    }
                })
        }
    }

    private fun refreshEffectReducer(): ObservableTransformer<FeedViewEvent.RefreshFeed, FeedViewAffect.ShowToast> {
        return ObservableTransformer {
            it.map {
                FeedViewAffect.ShowToast("updated")
            }
        }
    }
}