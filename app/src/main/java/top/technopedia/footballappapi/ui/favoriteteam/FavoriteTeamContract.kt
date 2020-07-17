package top.technopedia.footballappapi.ui.favoriteteam

import top.technopedia.footballappapi.model.Team

interface FavoriteTeamContract {

    interface View{
        fun displayTeams(teamList: List<Team>)
        fun hideLoading()
        fun showLoading()
        fun hideSwipeRefresh()
    }

    interface Presenter{
        fun getTeamData()
        fun onDestroy()
    }
}