package top.technopedia.footballappapi.ui.favoriteteam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import top.technopedia.footballappapi.R
import top.technopedia.footballappapi.adapter.TeamAdapter
import top.technopedia.footballappapi.extensions.hide
import top.technopedia.footballappapi.extensions.show
import top.technopedia.footballappapi.model.Team
import top.technopedia.footballappapi.model.repository.LocalRepositoryImpl
import top.technopedia.footballappapi.model.repository.TeamRepositoryImpl
import top.technopedia.footballappapi.network.FootballApiService
import top.technopedia.footballappapi.network.FootballRest
import top.technopedia.footballappapi.utils.AppSchedulerProvider

class FavoriteTeamFragment : Fragment(), FavoriteTeamContract.View {

    private var teamLists : MutableList<Team> = mutableListOf()
    private lateinit var mPresenter : FavoriteTeamPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = FootballApiService.getClient().create(FootballRest::class.java)
        val localRepositoryImpl = LocalRepositoryImpl(context!!)
        val teamRepositoryImpl = TeamRepositoryImpl(service)
        val scheduler = AppSchedulerProvider()
        mPresenter = FavoriteTeamPresenter(this, localRepositoryImpl, teamRepositoryImpl, scheduler)
        mPresenter.getTeamData()

        swiperefresh.setOnRefreshListener {
            mPresenter.getTeamData()
        }
    }


    override fun displayTeams(teamList: List<Team>) {
        teamLists.clear()
        teamLists.addAll(teamList)
        val layoutManager = GridLayoutManager(context, 3)
        rvTeam.layoutManager = layoutManager
        rvTeam.adapter = TeamAdapter(teamLists, context)
    }

    override fun hideLoading() {
        mainProgressBar.hide()
        rvTeam.visibility = View.VISIBLE
    }

    override fun showLoading() {
        mainProgressBar.show()
        rvTeam.visibility = View.GONE
    }

    override fun hideSwipeRefresh() {
        swiperefresh.isRefreshing = false
        mainProgressBar.hide()
        rvTeam.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }


}
