package com.fandataxidriver.ui.activity.main;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.SettingsResponse;
import com.fandataxidriver.data.network.model.TripResponse;
import com.fandataxidriver.data.network.model.UserResponse;

public interface MainIView extends MvpView {
    void onSuccess(UserResponse user);

    void onError(Throwable e);

    void onSuccessLogout(Object object);

    void onSuccess(TripResponse tripResponse);

    void onSuccess(SettingsResponse response);

    void onSettingError(Throwable e);

    void onSuccessProviderAvailable(Object object);

    void onSuccessFCM(Object object);

    void onSuccessLocationUpdate(TripResponse tripResponse);

}
