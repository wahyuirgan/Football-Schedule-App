package top.technopedia.footballappapi.model.repository

import io.reactivex.Flowable
import top.technopedia.footballappapi.model.Leagues

interface LeagueRepository {

    fun getLeagueData(id: String) : Flowable<Leagues>

}