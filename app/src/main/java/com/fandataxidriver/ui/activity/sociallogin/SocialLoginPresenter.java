package com.fandataxidriver.ui.activity.sociallogin;

import com.fandataxidriver.base.BasePresenter;
import com.fandataxidriver.data.network.APIClient;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SocialLoginPresenter<V extends SocialLoginIView> extends BasePresenter<V> implements SocialLoginIPresenter<V> {

    @Override
    public void loginGoogle(HashMap<String, Object> obj) {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .loginGoogle(obj)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }

    @Override
    public void loginFacebook(HashMap<String, Object> obj) {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .loginFacebook(obj)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }
}
