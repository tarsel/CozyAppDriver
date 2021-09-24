package com.fandataxidriver.ui.activity.request_money;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.RequestDataResponse;

public interface RequestMoneyIView extends MvpView {

    void onSuccess(RequestDataResponse response);
    void onSuccess(Object response);
    void onError(Throwable e);

}
