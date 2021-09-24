package com.fandataxidriver.ui.activity.wallet;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.UserResponse;
import com.fandataxidriver.data.network.model.WalletMoneyAddedResponse;
import com.fandataxidriver.data.network.model.WalletResponse;

public interface WalletIView extends MvpView {

    void onSuccess(WalletResponse response);

    void onSuccess(WalletMoneyAddedResponse response);

    void onError(Throwable e);


    void onSuccess(UserResponse userResponse);
}
