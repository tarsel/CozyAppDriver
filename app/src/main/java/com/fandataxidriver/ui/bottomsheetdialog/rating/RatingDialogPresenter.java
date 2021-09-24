package com.fandataxidriver.ui.bottomsheetdialog.rating;

import com.fandataxidriver.base.BasePresenter;
import com.fandataxidriver.data.network.APIClient;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RatingDialogPresenter<V extends RatingDialogIView> extends BasePresenter<V> implements RatingDialogIPresenter<V> {
    @Override
    public void rate(HashMap<String, Object> obj, Integer id) {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .ratingRequest(obj, id)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }
}
