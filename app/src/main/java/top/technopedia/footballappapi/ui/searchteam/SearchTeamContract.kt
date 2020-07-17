package top.technopedia.footballappapi.ui.searchteam

import top.technopedia.footballappapi.model.Team

interface SearchTeamContract {

    interface View{
        fun showLoading()
        fun hideLoading()
        fun displayTeam(teamList: List<Team>)
    }
    interface Presenter{
        fun searchTeam(query: String?)
        fun onDestroy()
    }
}