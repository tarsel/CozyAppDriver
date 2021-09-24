package com.fandataxidriver.ui.activity.forgot_password;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.ForgotResponse;

public interface ForgotIView extends MvpView {

    void onSuccess(ForgotResponse forgotResponse);
    void onError(Throwable e);
}
