package com.fandataxidriver.ui.fragment.status_flow;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.TimerResponse;

public interface StatusFlowIView extends MvpView {

    void onSuccess(Object object);

    void onWaitingTimeSuccess(TimerResponse object);

    void onError(Throwable e);
}
