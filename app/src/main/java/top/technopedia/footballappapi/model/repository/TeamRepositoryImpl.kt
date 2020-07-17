package top.technopedia.footballappapi.model.repository


import io.reactivex.Flowable
import io.reactivex.Observable
import top.technopedia.footballappapi.network.FootballRest
import top.technopedia.footballappapi.model.Teams


class TeamRepositoryImpl(private val footballRest: FootballRest) :
    TeamRepository {

    override fun getAllTeam(id: String) = footballRest.getAllTeam(id)
    override fun getTeams(id: String): Flowable<Teams> = footballRest.getAllTeam(id)
    override fun getTeamsDetail(id: String): Flowable<Teams> = footballRest.getTeam(id)
    override fun searchTeams(query: String?) = footballRest.getTeamBySearch(query)

}