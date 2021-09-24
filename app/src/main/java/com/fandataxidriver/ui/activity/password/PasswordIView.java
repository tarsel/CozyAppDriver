package com.fandataxidriver.ui.activity.password;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.ForgotResponse;
import com.fandataxidriver.data.network.model.User;

public interface PasswordIView extends MvpView {

    void onSuccess(ForgotResponse forgotResponse);

    void onSuccess(User object);

    void onError(Throwable e);
}
