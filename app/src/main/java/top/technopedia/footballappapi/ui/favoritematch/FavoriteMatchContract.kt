package top.technopedia.footballappapi.ui.favoritematch

import top.technopedia.footballappapi.model.Event

interface FavoriteMatchContract {
    interface View{
        fun hideLoading()
        fun showLoading()
        fun displayFootballMatch(matchList:List<Event>)
        fun hideSwipeRefresh()
    }

    interface Presenter{
        fun getFootballMatchData()
        fun onDestroyPresenter()
    }
}