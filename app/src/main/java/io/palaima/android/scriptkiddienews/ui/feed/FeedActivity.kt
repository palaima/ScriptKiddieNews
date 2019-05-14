package io.palaima.android.scriptkiddienews.ui.feed

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.palaima.android.scriptkiddienews.R
import io.palaima.android.scriptkiddienews.ui.base.ViewAffect
import io.palaima.android.scriptkiddienews.ui.base.ViewState
import io.palaima.android.scriptkiddienews.ui.feed.data.FeedViewAction
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

        // persisting state
        viewModel.onViewState().observe(this, Observer<ViewState> {
            when (it) {
                is FeedViewState.ShowFeed -> {
                    // todo show feed
                }
                else -> {
                    throw IllegalStateException("ViewState $it is not supported")
                }
            }
        })

        // one time events
        // toast, dialog, screen, etc
        viewModel.onViewAffect().observe(this, Observer<ViewAffect> {
            when (it) {
                is FeedViewAffect.ShowToast -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    throw IllegalStateException("ViewAffect $it is not supported")
                }
            }
        })

        // todo call on some UI action
        viewModel.postViewAction(FeedViewAction.RefreshFeed)
    }
}
