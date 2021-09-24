package com.fandataxidriver.ui.activity.splash;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.CheckVersion;

public interface SplashIView extends MvpView {

    void verifyAppInstalled();

    void onSuccess(Object user);

    void onSuccess(CheckVersion user);

    void onError(Throwable e);

    void onCheckVersionError(Throwable e);
}
