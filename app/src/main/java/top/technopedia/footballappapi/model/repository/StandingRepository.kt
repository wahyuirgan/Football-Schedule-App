package top.technopedia.footballappapi.model.repository

import io.reactivex.Flowable
import top.technopedia.footballappapi.model.Standings

interface StandingRepository {

    fun getStanding(l: String) : Flowable<Standings>

}