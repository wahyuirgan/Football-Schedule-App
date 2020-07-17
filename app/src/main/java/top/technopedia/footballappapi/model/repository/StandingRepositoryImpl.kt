package top.technopedia.footballappapi.model.repository

import io.reactivex.Flowable
import top.technopedia.footballappapi.model.Standings
import top.technopedia.footballappapi.network.FootballRest

class StandingRepositoryImpl(private val footballRest: FootballRest):
    StandingRepository {

    override fun getStanding(l: String): Flowable<Standings> = footballRest.getStanding(l)

}