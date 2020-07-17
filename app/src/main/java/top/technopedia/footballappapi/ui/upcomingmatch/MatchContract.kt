package top.technopedia.footballappapi.ui.upcomingmatch

import top.technopedia.footballappapi.model.Event
import top.technopedia.footballappapi.model.League


interface MatchContract {
    interface View{
        fun hideLoading()
        fun showLoading()
        fun showLeague(data:List<League>)
        fun displayFootballMatch(matchList:List<Event>)
    }

    interface Presenter{
        fun getLeagueDetailData(leagueId: String = "4328")
        fun getFootballMatchData(leagueName: String = "4328")
        fun onDestroyPresenter()

    }
}