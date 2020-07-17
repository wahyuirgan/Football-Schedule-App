package top.technopedia.footballappapi.ui.standing

import top.technopedia.footballappapi.model.Standing


interface StandingContract {
    interface View{
        fun hideLoading()
        fun showLoading()
        fun showStanding(l:List<Standing>)
        fun hideSwipeRefresh()
    }

    interface Presenter{
        fun getStandingData(l: String = "4328")
        fun onDestroyPresenter()

    }
}