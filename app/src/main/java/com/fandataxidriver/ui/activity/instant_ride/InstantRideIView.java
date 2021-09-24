package com.fandataxidriver.ui.activity.instant_ride;

import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.EstimateFare;
import com.fandataxidriver.data.network.model.TripResponse;

public interface InstantRideIView extends MvpView {

    void onSuccess(EstimateFare estimateFare);

    void onSuccess(TripResponse response);

    void onError(Throwable e);

}
