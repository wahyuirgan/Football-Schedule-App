package top.technopedia.footballappapi.ui.upcomingmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_upcoming_match.*
import kotlinx.android.synthetic.main.fragment_upcoming_match.mainProgressBar
import kotlinx.android.synthetic.main.fragment_upcoming_match.spinnerMatch
import top.technopedia.footballappapi.*
import top.technopedia.footballappapi.adapter.LeagueAdapter
import top.technopedia.footballappapi.adapter.MatchAdapter
import top.technopedia.footballappapi.extensions.hide
import top.technopedia.footballappapi.extensions.show
import top.technopedia.footballappapi.model.Event
import top.technopedia.footballappapi.model.League
import top.technopedia.footballappapi.model.repository.LeagueRepositoryImpl
import top.technopedia.footballappapi.model.repository.MatchRepositoryImpl
import top.technopedia.footballappapi.network.FootballApiService
import top.technopedia.footballappapi.network.FootballRest
import top.technopedia.footballappapi.utils.AppSchedulerProvider


class  UpcomingMatchFragment : Fragment(), MatchContract.View {

    lateinit var mPresenter : UpcomingMatchPresenter

    lateinit var leagueName: String

    private var matchLists : MutableList<Event> = mutableListOf()

    private var leagueLists: MutableList<League> = mutableListOf()

    override fun hideLoading() {
        mainProgressBar.hide()
        rvFootball.visibility = View.VISIBLE
    }

    override fun showLoading() {
        mainProgressBar.show()
        rvFootball.visibility = View.INVISIBLE
    }

    override fun displayFootballMatch(matchList: List<Event>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvFootball.layoutManager = layoutManager
        rvFootball.adapter = MatchAdapter(matchList, context)
    }

    override fun showLeague(data: List<League>) {
        leagueLists.clear()
        leagueLists.addAll(data)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvLeague.layoutManager = layoutManager
        rvLeague.adapter = LeagueAdapter(data, context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_upcoming_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = FootballApiService.getClient()
            .create(FootballRest::class.java)
        val request = MatchRepositoryImpl(service)
        val league = LeagueRepositoryImpl(service)
        val scheduler = AppSchedulerProvider()
        mPresenter = UpcomingMatchPresenter(
            this,
            request,
            league,
            scheduler
        )
        mPresenter.getLeagueDetailData()
        mPresenter.getFootballMatchData()
        val spinnerItems = resources.getStringArray(R.array.leagueArray)
        val spinnerAdapter = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, spinnerItems) }
        spinnerMatch.adapter = spinnerAdapter

        spinnerMatch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinnerMatch.selectedItem.toString()
                when(leagueName){
                    getString(R.string.english_league) -> mPresenter.getLeagueDetailData(getString(
                        R.string.english_league_id
                    ))
                    getString(R.string.german_league) -> mPresenter.getLeagueDetailData(getString(
                        R.string.german_league_id
                    ))
                    getString(R.string.italian_league) -> mPresenter.getLeagueDetailData(getString(
                        R.string.italian_league_id
                    ))
                    getString(R.string.french_league) -> mPresenter.getLeagueDetailData(getString(
                        R.string.french_league_id
                    ))
                    getString(R.string.spanish_league) -> mPresenter.getLeagueDetailData(getString(
                        R.string.spanish_league_id
                    ))
                    getString(R.string.netherland_league) -> mPresenter.getLeagueDetailData(getString(
                        R.string.netherland_league_id
                    ))
                    else -> mPresenter.getLeagueDetailData()
                }
                when(leagueName){
                    getString(R.string.english_league) -> mPresenter.getFootballMatchData(getString(
                        R.string.english_league_id
                    ))
                    getString(R.string.german_league) -> mPresenter.getFootballMatchData(getString(
                        R.string.german_league_id
                    ))
                    getString(R.string.italian_league) -> mPresenter.getFootballMatchData(getString(
                        R.string.italian_league_id
                    ))
                    getString(R.string.french_league) -> mPresenter.getFootballMatchData(getString(
                        R.string.french_league_id
                    ))
                    getString(R.string.spanish_league) -> mPresenter.getFootballMatchData(getString(
                        R.string.spanish_league_id
                    ))
                    getString(R.string.netherland_league) -> mPresenter.getFootballMatchData(getString(
                        R.string.netherland_league_id
                    ))
                    else -> mPresenter.getFootballMatchData()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }
}