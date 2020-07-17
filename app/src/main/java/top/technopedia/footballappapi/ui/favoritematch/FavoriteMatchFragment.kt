package top.technopedia.footballappapi.ui.favoritematch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_match_favorite.*
import top.technopedia.footballappapi.R
import top.technopedia.footballappapi.adapter.MatchAdapter
import top.technopedia.footballappapi.extensions.hide
import top.technopedia.footballappapi.extensions.show
import top.technopedia.footballappapi.model.Event
import top.technopedia.footballappapi.model.repository.LocalRepositoryImpl
import top.technopedia.footballappapi.model.repository.MatchRepositoryImpl
import top.technopedia.footballappapi.network.FootballApiService
import top.technopedia.footballappapi.network.FootballRest
import top.technopedia.footballappapi.utils.AppSchedulerProvider

class FavoriteMatchFragment : Fragment(), FavoriteMatchContract.View {
    private var matchLists : MutableList<Event> = mutableListOf()
    private lateinit var mMatchPresenter : FavoriteMatchPresenter

    override fun hideLoading() {
        mainProgressBar.hide()
        rvFootballFav.visibility = View.VISIBLE
    }

    override fun showLoading() {
        mainProgressBar.show()
        rvFootballFav.visibility = View.INVISIBLE
    }

    override fun displayFootballMatch(matchList: List<Event>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvFootballFav.layoutManager = layoutManager
        rvFootballFav.adapter = MatchAdapter(matchList, context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = FootballApiService.getClient().create(FootballRest::class.java)
        val request = MatchRepositoryImpl(service)
        val localRepositoryImpl = LocalRepositoryImpl(context!!)
        val scheduler = AppSchedulerProvider()
        mMatchPresenter = FavoriteMatchPresenter(this, request, localRepositoryImpl, scheduler)
        mMatchPresenter.getFootballMatchData()

        swiperefreshFav.setOnRefreshListener {
            mMatchPresenter.getFootballMatchData()
        }

    }

    override fun hideSwipeRefresh() {
        swiperefreshFav.isRefreshing = false
        mainProgressBar.hide()
        rvFootballFav.visibility = View.VISIBLE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_favorite, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        mMatchPresenter.onDestroyPresenter()
    }


}
