package com.fandataxidriver.ui.activity.sociallogin;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.Token;

public interface SocialLoginIView extends MvpView {

    void onSuccess(Token token);
    void onError(Throwable e);
}
