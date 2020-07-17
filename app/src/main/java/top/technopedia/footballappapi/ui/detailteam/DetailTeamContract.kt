package top.technopedia.footballappapi.ui.detailteam

import top.technopedia.footballappapi.model.db.FavoriteTeam

interface TeamDetailContract {

    interface View{
        fun setFavoriteState(favList:List<FavoriteTeam>)
    }

    interface Presenter{
        fun deleteTeam(id:String)
        fun checkTeam(id:String)
        fun insertTeam(id: String, imgUrl: String)
    }
}