package top.technopedia.footballappapi.ui.standing

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import top.technopedia.footballappapi.model.Standings
import top.technopedia.footballappapi.model.repository.StandingRepositoryImpl
import top.technopedia.footballappapi.utils.SchedulerProvider
import java.util.*

class StandingPresenter(val mView :  StandingContract.View,
                        private val standingRepositoryImpl: StandingRepositoryImpl,
                        private val scheduler: SchedulerProvider
) : StandingContract.Presenter {


    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }

    private val compositeDisposable = CompositeDisposable()

    override fun getStandingData(l: String) {
        mView.showLoading()
        compositeDisposable.add(standingRepositoryImpl.getStanding(l)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribeWith(object : ResourceSubscriber<Standings>(){
                override fun onComplete() {
                    mView.hideLoading()
                }

                override fun onNext(t: Standings) {
                    mView.showStanding(t.standings)
                }

                override fun onError(e: Throwable) {
                    mView.hideLoading()
                    mView.showStanding(Collections.emptyList())
                }

            })
        )
    }

}