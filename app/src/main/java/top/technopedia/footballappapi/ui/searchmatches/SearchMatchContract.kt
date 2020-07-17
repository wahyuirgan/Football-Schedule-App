package top.technopedia.footballappapi.ui.searchmatches

import top.technopedia.footballappapi.model.Event

interface SearchMatchContract {

    interface View{
        fun showLoading()
        fun hideLoading()
        fun displayMatch(matchList: List<Event>)
    }
    interface Presenter{
        fun searchMatch(query: String?)
        fun onDestroy()
    }
}