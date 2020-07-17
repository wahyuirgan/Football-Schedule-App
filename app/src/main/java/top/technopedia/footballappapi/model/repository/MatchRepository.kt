package top.technopedia.footballappapi.model.repository

import io.reactivex.Flowable
import top.technopedia.footballappapi.model.FootballMatch
import top.technopedia.footballappapi.model.SearchedMatches

interface MatchRepository {

    fun getFootballMatch(id : String) : Flowable<FootballMatch>

    fun getUpcomingMatch(id : String) : Flowable<FootballMatch>

    fun getEventById(id: String) : Flowable<FootballMatch>

    fun searchMatches(query: String?) : Flowable<SearchedMatches>
}