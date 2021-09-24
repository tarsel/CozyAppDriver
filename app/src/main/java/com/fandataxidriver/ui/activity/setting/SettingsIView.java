package com.fandataxidriver.ui.activity.setting;

import com.fandataxidriver.base.MvpView;

public interface SettingsIView extends MvpView {

    void onSuccess(Object o);

    void onError(Throwable e);

}
