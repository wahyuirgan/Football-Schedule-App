package top.technopedia.footballappapi.ui.detailmatch


import top.technopedia.footballappapi.model.db.FavoriteMatch


interface DetailMatchContract {

    interface View{
        fun showBadgeImageHome(string: String)
        fun showBadgeImageAway(string: String)
        fun setFavoriteState(favList:List<FavoriteMatch>)
    }

    interface Presenter{
        fun getBadge(id: String, team: Int)
        fun deleteMatch(id:String)
        fun checkMatch(id:String)
        fun insertMatch(eventId: String, homeId: String, awayId: String)
        fun onDestroyPresenter()
    }
}