package com.fandataxidriver.ui.activity.profile;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.SettingsResponse;
import com.fandataxidriver.data.network.model.UserResponse;

public interface ProfileIView extends MvpView {

    void onSuccess(UserResponse user);

    void onSuccessUpdate(UserResponse object);

    void onError(Throwable e);

    void onSuccessPhoneNumber(Object object);

    void onVerifyPhoneNumberError(Throwable e);

    void onSuccess(SettingsResponse response);

}
