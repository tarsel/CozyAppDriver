package com.fandataxidriver.ui.activity.upcoming_detail;


import com.fandataxidriver.base.MvpView;
import com.fandataxidriver.data.network.model.HistoryDetail;

public interface UpcomingTripDetailIView extends MvpView {

    void onSuccess(HistoryDetail historyDetail);
    void onError(Throwable e);
}
