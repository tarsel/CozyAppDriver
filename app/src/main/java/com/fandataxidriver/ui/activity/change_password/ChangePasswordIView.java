package com.fandataxidriver.ui.activity.change_password;

import com.fandataxidriver.base.MvpView;

public interface ChangePasswordIView extends MvpView {


    void onSuccess(Object object);
    void onError(Throwable e);
}
