package top.technopedia.footballappapi.ui.searchmatches


import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import top.technopedia.footballappapi.model.repository.MatchRepositoryImpl
import top.technopedia.footballappapi.utils.SchedulerProvider
import top.technopedia.footballappapi.model.SearchedMatches
import java.util.*

class SearchMatchPresenter(val mView: SearchMatchContract.View,
                           private val matchRepositoryImpl: MatchRepositoryImpl,
                           private val schedulerProvider: SchedulerProvider
): SearchMatchContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun searchMatch(query: String?) {
        mView.showLoading()
        compositeDisposable.add(matchRepositoryImpl.searchMatches(query)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(object: ResourceSubscriber<SearchedMatches>(){
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: SearchedMatches?) {
                        t?.events?.filter { it.strSport == "Soccer" }?.let { mView.displayMatch(it) }
                    }

                    override fun onError(t: Throwable?) {
                        mView.displayMatch(Collections.emptyList())
                        mView.hideLoading()
                    }

                })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }


}