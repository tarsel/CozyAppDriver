package com.fandataxidriver.ui.activity.reset_password;

import com.fandataxidriver.base.MvpView;

public interface ResetIView extends MvpView {

    void onSuccess(Object object);
    void onError(Throwable e);
}
