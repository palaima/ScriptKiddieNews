package io.palaima.android.scriptkiddienews.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.palaima.android.scriptkiddienews.R
import io.palaima.android.scriptkiddienews.ui.base.ViewEffect
import io.palaima.android.scriptkiddienews.ui.base.ViewState
import io.palaima.android.scriptkiddienews.ui.feed.data.FeedViewAffect
import io.palaima.android.scriptkiddienews.ui.feed.data.FeedViewEvent
import io.palaima.android.scriptkiddienews.ui.feed.data.FeedViewModel
import io.palaima.android.scriptkiddienews.ui.feed.data.FeedViewState

import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: FeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)

        // persisting state
        viewModel.onViewState().observe(this, Observer<ViewState> { renter(it) })

        // one time events
        // toast, dialog, screen, etc
        viewModel.onViewEffect().observe(this, Observer<ViewEffect> { trigger(it) })

        // todo call on some UI action
        viewModel.processViewEvent(FeedViewEvent.RefreshFeed)

        fab.setOnClickListener { view ->
            viewModel.processViewEvent(FeedViewEvent.RefreshFeed)
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun renter(state: ViewState) {
        when (state) {
            is FeedViewState.ShowFeed -> {
                state.items.forEach {
                    Timber.d(it.title)
                }
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
