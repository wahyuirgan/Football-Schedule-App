package top.technopedia.footballappapi.model.repository

import io.reactivex.Flowable
import top.technopedia.footballappapi.network.FootballRest
import top.technopedia.footballappapi.model.Leagues

class LeagueRepositoryImpl(private val footballRest: FootballRest):
    LeagueRepository {

    override fun getLeagueData(id: String): Flowable<Leagues> = footballRest.getDetailLeague(id)

}