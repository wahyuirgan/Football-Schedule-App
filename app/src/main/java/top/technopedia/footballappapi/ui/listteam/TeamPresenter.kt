package top.technopedia.footballappapi.ui.listteam

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import top.technopedia.footballappapi.model.Teams
import top.technopedia.footballappapi.model.repository.TeamRepositoryImpl
import top.technopedia.footballappapi.utils.SchedulerProvider
import java.util.*

class TeamPresenter(val mView : TeamContract.View, private val teamRepositoryImpl: TeamRepositoryImpl,
                    val scheduler: SchedulerProvider
): TeamContract.Presenter {

    private val compositeDisposable = CompositeDisposable()
    override fun getTeamData(leagueName: String) {
        mView.showLoading()
        compositeDisposable.add(teamRepositoryImpl.getAllTeam(leagueName)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribeWith(object: ResourceSubscriber<Teams>(){
                override fun onComplete() {
                    mView.hideLoading()
                }

                override fun onNext(t: Teams) {
                    mView.displayTeams(t.teams)
                }

                override fun onError(t: Throwable?) {
                    mView.displayTeams(Collections.emptyList())
                    mView.hideLoading()
                }

            })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

}