package io.palaima.android.scriptkiddienews.ui.feed

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.palaima.android.scriptkiddienews.R
import io.palaima.android.scriptkiddienews.ui.base.ViewEffect
import io.palaima.android.scriptkiddienews.ui.base.ViewState
import io.palaima.android.scriptkiddienews.ui.feed.data.FeedViewEvent
import io.palaima.android.scriptkiddienews.ui.feed.data.FeedViewAffect
import io.palaima.android.scriptkiddienews.ui.feed.data.FeedViewModel
import io.palaima.android.scriptkiddienews.ui.feed.data.FeedViewState
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {

    private lateinit var viewModel: FeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)

        // state changes
        viewModel.onViewState().observe(this, Observer<ViewState> { render(it) })

        // one time events
        // toast, dialog, screen, etc
        viewModel.onViewEffect().observe(this, Observer<ViewEffect> { trigger(it) })

        // todo call on some UI action
        viewModel.processViewEvent(FeedViewEvent.RefreshFeed)
    }

    private fun render(state: ViewState) {
        when (state) {
            is FeedViewState.ShowFeed -> {
                // todo show feed
            }
            else -> {
                throw IllegalStateException("ViewState $state is not supported")
            }
        }
    }

    private fun trigger(effect: ViewEffect) {
        when (effect) {
            is FeedViewAffect.ShowToast -> {
                Toast.makeText(this, effect.message, Toast.LENGTH_SHORT).show()
            }
            else -> {
                throw IllegalStateException("ViewEffect $effect is not supported")
            }
        }
    }
}
