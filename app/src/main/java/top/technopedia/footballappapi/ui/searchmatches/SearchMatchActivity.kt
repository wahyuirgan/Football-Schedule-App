package top.technopedia.footballappapi.ui.searchmatches

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search_match.*
import top.technopedia.footballappapi.*
import top.technopedia.footballappapi.adapter.MatchAdapter
import top.technopedia.footballappapi.extensions.hide
import top.technopedia.footballappapi.extensions.show
import top.technopedia.footballappapi.model.Event
import top.technopedia.footballappapi.model.repository.MatchRepositoryImpl
import top.technopedia.footballappapi.network.FootballApiService
import top.technopedia.footballappapi.network.FootballRest
import top.technopedia.footballappapi.utils.AppSchedulerProvider

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SearchMatchActivity : AppCompatActivity(),
    SearchMatchContract.View {

    private var matchLists : MutableList<Event> = mutableListOf()
    lateinit var mPresenter: SearchMatchContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        val query = intent.getStringExtra("query")
        Log.v("test", query)
        val service = FootballApiService.getClient()
            .create(FootballRest::class.java)
        val request = MatchRepositoryImpl(service)
        val scheduler = AppSchedulerProvider()
        mPresenter = SearchMatchPresenter(
            this,
            request,
            scheduler
        )
        mPresenter.searchMatch(query)

    }

    override fun showLoading() {
        mainProgressBar.show()
        rvFootball.hide()
    }

    override fun hideLoading() {
        mainProgressBar.hide()
        rvFootball.show()
    }

    override fun displayMatch(matchList: List<Event>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rvFootball.layoutManager = layoutManager
        rvFootball.adapter =
            MatchAdapter(matchList, applicationContext)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search Matches"

        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mPresenter.searchMatch(newText)
                return false
            }
        })

        return true

    }
}