package com.fandataxidriver.ui.activity.upcoming_detail;

import com.fandataxidriver.base.BasePresenter;
import com.fandataxidriver.data.network.APIClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UpcomingTripDetailPresenter<V extends UpcomingTripDetailIView> extends BasePresenter<V>
        implements UpcomingTripDetailIPresenter<V> {

    @Override
    public void getUpcomingDetail(String request_id) {
        getCompositeDisposable().add(
                APIClient
                        .getAPIClient()
                        .getUpcomingDetail(request_id)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                trendsResponse -> getMvpView().onSuccess(trendsResponse),
                                throwable -> getMvpView().onError(throwable)
                        )
        );
    }
}
