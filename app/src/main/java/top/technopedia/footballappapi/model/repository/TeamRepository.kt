package top.technopedia.footballappapi.model.repository


import io.reactivex.Flowable
import io.reactivex.Observable
import top.technopedia.footballappapi.model.SearchedTeams
import top.technopedia.footballappapi.model.Teams

interface TeamRepository {

    fun getTeams(id : String = "0") : Flowable<Teams>

    fun getTeamsDetail(id : String = "0") : Flowable<Teams>

    fun getAllTeam(id: String) : Flowable<Teams>

    fun searchTeams(query: String?) : Flowable<SearchedTeams>
}