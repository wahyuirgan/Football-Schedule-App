package top.technopedia.footballappapi.ui.lastmatch


import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import top.technopedia.footballappapi.model.FootballMatch
import top.technopedia.footballappapi.model.Leagues
import top.technopedia.footballappapi.model.repository.LeagueRepositoryImpl
import top.technopedia.footballappapi.model.repository.MatchRepositoryImpl
import top.technopedia.footballappapi.ui.upcomingmatch.MatchContract
import top.technopedia.footballappapi.utils.SchedulerProvider
import java.util.*


class LastMatchPresenter(val mView : MatchContract.View,
                         private val matchRepositoryImpl: MatchRepositoryImpl,
                         private val leagueRepositoryImpl: LeagueRepositoryImpl,
                         private val scheduler: SchedulerProvider
) : MatchContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getFootballMatchData(leagueName: String) {
        mView.showLoading()
        compositeDisposable.add(matchRepositoryImpl.getFootballMatch(leagueName)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object : ResourceSubscriber<FootballMatch>(){
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: FootballMatch) {
                        mView.displayFootballMatch(t.events)
                    }

                    override fun onError(e: Throwable) {
                        mView.hideLoading()
                        mView.displayFootballMatch(Collections.emptyList())
                    }

                })
        )
    }

    override fun getLeagueDetailData(leagueId: String) {
        mView.showLoading()
        compositeDisposable.add(leagueRepositoryImpl.getLeagueData(leagueId)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribeWith(object : ResourceSubscriber<Leagues>(){
                override fun onComplete() {
                    mView.hideLoading()
                }

                override fun onNext(t: Leagues) {
                    mView.showLeague(t.leagues)
                }

                override fun onError(e: Throwable) {
                    mView.hideLoading()
                    mView.showLeague(Collections.emptyList())
                }

            })
        )
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}