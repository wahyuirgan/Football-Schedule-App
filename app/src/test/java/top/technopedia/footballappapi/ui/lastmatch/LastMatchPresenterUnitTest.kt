package top.technopedia.footballappapi.ui.lastmatch

import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import top.technopedia.footballappapi.model.Event
import top.technopedia.footballappapi.model.FootballMatch
import top.technopedia.footballappapi.model.League
import top.technopedia.footballappapi.model.Leagues
import top.technopedia.footballappapi.model.repository.LeagueRepositoryImpl
import top.technopedia.footballappapi.model.repository.MatchRepositoryImpl
import top.technopedia.footballappapi.ui.upcomingmatch.MatchContract
import top.technopedia.footballappapi.utils.SchedulerProvider
import top.technopedia.footballappapi.utils.TestingSchedulerProvider

class LastMatchPresenterUnitTest {

    @Mock
    lateinit var mView: MatchContract.View

    @Mock
    lateinit var matchRepositoryImpl: MatchRepositoryImpl

    @Mock
    lateinit var leagueRepositoryImpl: LeagueRepositoryImpl

    private lateinit var scheduler: SchedulerProvider

    private lateinit var mPresenter: LastMatchPresenter

    private lateinit var match : FootballMatch

    private lateinit var league : Leagues

    private lateinit var footballMatch: Flowable<FootballMatch>

    private lateinit var leagueList: Flowable<Leagues>

    private val event = mutableListOf<Event>()

    private val leagues = mutableListOf<League>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestingSchedulerProvider()
        match = FootballMatch(event)
        league = Leagues(leagues)
        footballMatch = Flowable.just(match)
        leagueList = Flowable.just(league)
        mPresenter = LastMatchPresenter(mView, matchRepositoryImpl, leagueRepositoryImpl, scheduler)
        Mockito.`when`(matchRepositoryImpl.getFootballMatch("4328")).thenReturn(footballMatch)
        Mockito.`when`(leagueRepositoryImpl.getLeagueData("4328")).thenReturn(leagueList)
    }

    @Test
    fun getFootballMatchData() {
        mPresenter.getFootballMatchData()
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).displayFootballMatch(event)
        Mockito.verify(mView).hideLoading()
    }

    @Test
    fun getLeagueDetailData() {
        mPresenter.getLeagueDetailData()
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).showLeague(leagues)
        Mockito.verify(mView).hideLoading()
    }

}