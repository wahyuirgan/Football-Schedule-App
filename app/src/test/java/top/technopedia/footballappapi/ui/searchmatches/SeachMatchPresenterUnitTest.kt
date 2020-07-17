package top.technopedia.footballappapi.ui.searchmatches

import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import top.technopedia.footballappapi.model.Event
import top.technopedia.footballappapi.model.SearchedMatches
import top.technopedia.footballappapi.model.repository.MatchRepositoryImpl
import top.technopedia.footballappapi.utils.SchedulerProvider
import top.technopedia.footballappapi.utils.TestingSchedulerProvider

class SeachMatchPresenterUnitTest {

    @Mock
    lateinit var mView: SearchMatchContract.View

    @Mock
    lateinit var matchRepositoryImpl: MatchRepositoryImpl

    private lateinit var scheduler: SchedulerProvider

    private lateinit var mPresenter: SearchMatchPresenter

    private lateinit var search : SearchedMatches

    private lateinit var searchMatch: Flowable<SearchedMatches>

    private val event = mutableListOf<Event>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestingSchedulerProvider()
        search = SearchedMatches(event)
        searchMatch = Flowable.just(search)
        mPresenter = SearchMatchPresenter(mView, matchRepositoryImpl, scheduler)
        Mockito.`when`(matchRepositoryImpl.searchMatches("Arsenal")).thenReturn(searchMatch)
    }

    @Test
    fun searchMatch() {
        mPresenter.searchMatch("Arsenal")
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).displayMatch(event)
        Mockito.verify(mView).hideLoading()
    }

}